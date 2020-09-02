package com.achesnovitskiy.breeds.ui.breeds

import android.os.Handler
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.achesnovitskiy.breeds.domain.Repository
import com.achesnovitskiy.breeds.ui.entities.PresentationBreed
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface BreedsViewModel {

    val breedsObservable: Observable<List<PresentationBreed>>

    val reposIsSearchObservable: Observable<Boolean>

    val loadingStateObservable: Observable<LoadingState>

    val refreshObserver: Observer<Unit>

    val searchQueryObserver: Observer<String>

    val searchToggleObserver: Observer<Boolean>
}

class BreedsViewModelImpl @Inject constructor(private val repository: Repository) :
    ViewModel(), BreedsViewModel {

    private val reposBehaviorSubject: BehaviorSubject<List<PresentationBreed>> = BehaviorSubject.create()

    private val searchQueryBehaviorSubject: BehaviorSubject<String> = BehaviorSubject.create()

    private val reposIsSearchBehaviorSubject: BehaviorSubject<Boolean> =
        BehaviorSubject.createDefault(false)

    private val loadingStateBehaviorSubject: BehaviorSubject<LoadingState> =
        BehaviorSubject.create()

    override val breedsObservable: Observable<List<PresentationBreed>>
        get() = Observable
            .combineLatest(
                reposBehaviorSubject,
                searchQueryBehaviorSubject,
                BiFunction { repos: List<PresentationBreed>, searchQuery: String ->
                    repos.filter { repo ->
                        repo.name.contains(searchQuery, true)
                    }
                }
            )

    override val reposIsSearchObservable: Observable<Boolean>
        get() = reposIsSearchBehaviorSubject

    override val loadingStateObservable: Observable<LoadingState>
        get() = loadingStateBehaviorSubject

    override val refreshObserver: PublishSubject<Unit> = PublishSubject.create()

    override val searchQueryObserver: PublishSubject<String> = PublishSubject.create()

    override val searchToggleObserver: PublishSubject<Boolean> = PublishSubject.create()

    init {
        repository.reposObservable
            .subscribeOn(Schedulers.io())
            .subscribe(reposBehaviorSubject)

        searchQueryObserver
            .subscribeOn(Schedulers.io())
            .subscribe(searchQueryBehaviorSubject)

        searchToggleObserver
            .subscribeOn(Schedulers.io())
            .subscribe(reposIsSearchBehaviorSubject)

        refreshObserver
            .switchMap {
                repository.refreshCompletable
                    .andThen(
                        Observable.just(
                            LoadingState(
                                isLoading = false,
                                errorRes = null
                            )
                        )
                    )
                    .startWith(
                        LoadingState(
                            isLoading = true,
                            errorRes = null
                        )
                    )
                    .onErrorReturnItem(
                        LoadingState(
                            isLoading = false,
                            errorRes = R.string.repos_update_error_message
                        )
                    )
            }
            .subscribeOn(Schedulers.io())
            .subscribe(loadingStateBehaviorSubject)

        // Without Handler().postDelayed no update occurs at startup of the fragment
        Handler().postDelayed(
            {
                refreshObserver.onNext(Unit)
            },
            100L
        )
    }
}

data class LoadingState(
    val isLoading: Boolean,
    @StringRes val errorRes: Int?
)
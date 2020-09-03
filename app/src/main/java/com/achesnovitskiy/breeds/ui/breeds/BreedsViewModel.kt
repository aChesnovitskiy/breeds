package com.achesnovitskiy.breeds.ui.breeds

import androidx.lifecycle.ViewModel
import com.achesnovitskiy.breeds.domain.Repository
import io.reactivex.Observable
import javax.inject.Inject

interface BreedsViewModel {

    val breedsObservable: Observable<String>
}

class BreedsViewModelImpl @Inject constructor(private val repository: Repository) :
    ViewModel(), BreedsViewModel {

    override val breedsObservable: Observable<String>
        get() = repository.breedsObservable
}
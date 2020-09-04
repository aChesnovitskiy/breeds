package com.achesnovitskiy.breeds.ui.breeds

import androidx.lifecycle.ViewModel
import com.achesnovitskiy.breeds.domain.Repository
import com.achesnovitskiy.breeds.ui.entities.Breed
import io.reactivex.Observable
import javax.inject.Inject

interface BreedsViewModel {

    val breedsObservable: Observable<List<Breed>>
}

class BreedsViewModelImpl @Inject constructor(private val repository: Repository) :
    ViewModel(), BreedsViewModel {

    override val breedsObservable: Observable<List<Breed>>
        get() = repository.breedsObservable
}
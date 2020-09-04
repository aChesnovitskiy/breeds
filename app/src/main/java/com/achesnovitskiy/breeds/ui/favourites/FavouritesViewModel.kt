package com.achesnovitskiy.breeds.ui.favourites

import androidx.lifecycle.ViewModel
import com.achesnovitskiy.breeds.domain.Repository
import com.achesnovitskiy.breeds.ui.dto.Breed
import io.reactivex.Observable
import javax.inject.Inject

interface FavouritesViewModel {

    val favouritesObservable: Observable<List<Breed>>
}

class FavouritesViewModelImpl @Inject constructor(private val repository: Repository) :
    ViewModel(), FavouritesViewModel {

    override val favouritesObservable: Observable<List<Breed>>
        get() = repository.breedsObservable
}
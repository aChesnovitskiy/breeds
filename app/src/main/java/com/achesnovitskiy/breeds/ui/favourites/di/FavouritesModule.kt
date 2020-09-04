package com.achesnovitskiy.breeds.ui.favourites.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.achesnovitskiy.breeds.domain.Repository
import com.achesnovitskiy.breeds.ui.di.ViewScope
import com.achesnovitskiy.breeds.ui.favourites.FavouritesViewModel
import com.achesnovitskiy.breeds.ui.favourites.FavouritesViewModelImpl
import dagger.Module
import dagger.Provides

@Module
class FavouritesModule(
    private val viewModelStoreOwner: ViewModelStoreOwner
) {

    @Provides
    @ViewScope
    fun provideFavouritesViewModel(repository: Repository): FavouritesViewModel =
        ViewModelProvider(
            viewModelStoreOwner,
            FavouritesViewModelFactory(repository)
        ).get(FavouritesViewModelImpl::class.java)

    class FavouritesViewModelFactory(private val repository: Repository) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            FavouritesViewModelImpl(repository) as T
    }
}
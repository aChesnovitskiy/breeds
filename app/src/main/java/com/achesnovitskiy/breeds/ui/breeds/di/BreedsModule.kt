package com.achesnovitskiy.breeds.ui.breeds.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.achesnovitskiy.breeds.domain.Repository
import com.achesnovitskiy.breeds.ui.breeds.BreedsViewModel
import com.achesnovitskiy.breeds.ui.breeds.BreedsViewModelImpl
import com.achesnovitskiy.breeds.ui.di.ViewScope
import dagger.Module
import dagger.Provides

@Module
class BreedsModule(
    private val viewModelStoreOwner: ViewModelStoreOwner
) {

    @Provides
    @ViewScope
    fun provideCatsViewModel(repository: Repository): BreedsViewModel =
        ViewModelProvider(
            viewModelStoreOwner,
            BreedsViewModelFactory(repository)
        ).get(BreedsViewModelImpl::class.java)

    class BreedsViewModelFactory(private val repository: Repository) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            BreedsViewModelImpl(repository) as T
    }
}
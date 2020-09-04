package com.achesnovitskiy.breeds.ui.favourites.di

import com.achesnovitskiy.breeds.app.di.AppComponent
import com.achesnovitskiy.breeds.ui.di.ViewScope
import com.achesnovitskiy.breeds.ui.favourites.FavouritesFragment
import dagger.Component

@ViewScope
@Component(dependencies = [AppComponent::class], modules = [FavouritesModule::class])
interface FavouritesComponent {

    fun inject(fragment: FavouritesFragment)
}
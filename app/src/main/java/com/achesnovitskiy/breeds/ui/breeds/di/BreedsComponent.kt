package com.achesnovitskiy.breeds.ui.breeds.di

import com.achesnovitskiy.breeds.app.di.AppComponent
import com.achesnovitskiy.breeds.ui.breeds.BreedsFragment
import com.achesnovitskiy.breeds.ui.di.ViewScope
import dagger.Component

@ViewScope
@Component(dependencies = [AppComponent::class], modules = [BreedsModule::class])
interface BreedsComponent {

    fun inject(fragment: BreedsFragment)
}
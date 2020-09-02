package com.achesnovitskiy.breeds.app.di

import android.content.Context
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    val context: Context

    val repository: Repository

    val api: Api
}
package com.achesnovitskiy.breeds.app.di

import android.content.Context
import com.achesnovitskiy.breeds.data.api.Api
import com.achesnovitskiy.breeds.data.di.DataModule
import com.achesnovitskiy.breeds.domain.Repository
import com.achesnovitskiy.breeds.domain.di.RepositoryModule
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
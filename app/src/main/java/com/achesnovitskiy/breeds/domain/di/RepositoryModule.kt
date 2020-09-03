package com.achesnovitskiy.breeds.domain.di

import com.achesnovitskiy.breeds.data.api.Api
//import com.achesnovitskiy.breeds.data.db.Db
import com.achesnovitskiy.breeds.domain.Repository
import com.achesnovitskiy.breeds.domain.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(api: Api/*, db: Db*/): Repository = RepositoryImpl(api/*, db*/)
}
package com.achesnovitskiy.breeds.data.di

import android.content.Context
import androidx.room.Room
import com.achesnovitskiy.breeds.data.api.Api
//import com.achesnovitskiy.breeds.data.db.Db
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideApi(): Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(Api::class.java)

//    @Provides
//    @Singleton
//    fun provideDb(context: Context): Db = Room.databaseBuilder(
//        context,
//        Db::class.java,
//        DATABASE_NAME
//    ).build()
//
    companion object {
        const val BASE_URL = "https://dog.ceo/api/"

        const val DATABASE_NAME = "database.db"
    }
}
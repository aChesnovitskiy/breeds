package com.achesnovitskiy.breeds.app

import android.app.Application
import com.achesnovitskiy.breeds.app.di.AppComponent
import com.achesnovitskiy.breeds.app.di.AppModule
import com.achesnovitskiy.breeds.app.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(
                AppModule(context = this)
            )
            .build()
    }
}
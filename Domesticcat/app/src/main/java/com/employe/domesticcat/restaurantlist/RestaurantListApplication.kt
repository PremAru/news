package com.employe.domesticcat.zomato

import android.app.Application
import com.employe.domesticcat.di.AppComponent
import com.employe.domesticcat.di.DaggerAppComponent
import com.squareup.picasso.BuildConfig
import timber.log.Timber

class UserInfoApplication : Application() {

    val appComponent: AppComponent by lazy { initalizeAppComponent() }

    private fun initalizeAppComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
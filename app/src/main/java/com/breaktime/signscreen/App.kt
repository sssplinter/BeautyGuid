package com.breaktime.signscreen

import android.app.Application
import android.content.Context
import com.breaktime.signscreen.di.AppComponent
import com.breaktime.signscreen.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }
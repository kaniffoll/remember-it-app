package com.kaniffoll.rememberit.presentation

import android.app.Application
import com.kaniffoll.rememberit.di.AppComponent
import com.kaniffoll.rememberit.di.AppComponentProvider
import com.kaniffoll.rememberit.di.DaggerAppComponent

class RememberItApp: Application(), AppComponentProvider {

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }

    override fun onCreate() {
        applicationContext
        super.onCreate()
    }
}
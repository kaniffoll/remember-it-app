package com.kaniffoll.rememberit.di

import android.content.Context
import com.kaniffoll.rememberit.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import jakarta.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, NotificationSchedulerModule::class, DBModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
}
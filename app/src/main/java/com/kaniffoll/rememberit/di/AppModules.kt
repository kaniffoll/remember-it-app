package com.kaniffoll.rememberit.di

import android.content.Context
import androidx.room.Room
import com.kaniffoll.rememberit.data.NoteRepositoryImpl
import com.kaniffoll.rememberit.data.database.DBResources.DATABASE_NAME
import com.kaniffoll.rememberit.data.database.NoteDatabase
import com.kaniffoll.rememberit.data.workers.NotificationSchedulerImpl
import com.kaniffoll.rememberit.domain.NoteRepository
import com.kaniffoll.rememberit.domain.NotificationScheduler
import dagger.Binds
import dagger.Module
import dagger.Provides
import jakarta.inject.Singleton

@Module
interface RepositoryModule {
    @Binds
    fun bindsNoteRepository(impl: NoteRepositoryImpl): NoteRepository
}

@Module
interface NotificationSchedulerModule {
    @Binds
    fun bindsNotificationScheduler(impl: NotificationSchedulerImpl): NotificationScheduler
}

@Module
object DBModule {

    @Provides
    @Singleton
    fun providesDataBase(context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            name = DATABASE_NAME
        ).fallbackToDestructiveMigration(true).build()
    }

    @Provides
    @Singleton
    fun providesDao(database: NoteDatabase) = database.dao
}
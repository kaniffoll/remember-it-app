package com.kaniffoll.rememberit.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaniffoll.rememberit.data.database.DBResources.DATABASE_VERSION
import com.kaniffoll.rememberit.data.database.NoteDao
import com.kaniffoll.rememberit.data.model.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = DATABASE_VERSION
)
abstract class NoteDatabase: RoomDatabase(){

    abstract val dao: NoteDao
}
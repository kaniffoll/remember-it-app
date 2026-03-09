package com.kaniffoll.rememberit.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaniffoll.rememberit.data.database.NoteDao
import com.kaniffoll.rememberit.data.model.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase(){

    abstract val dao: NoteDao
}
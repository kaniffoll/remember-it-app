package com.kaniffoll.rememberit.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.kaniffoll.rememberit.data.model.NoteEntity

@Dao
interface NoteDao {

    @Upsert
    suspend fun upsertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM note")
    suspend fun getNotes(): List<NoteEntity>
}
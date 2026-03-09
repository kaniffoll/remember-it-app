package com.kaniffoll.rememberit.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int  = 0,
    val title: String,
    val text: String,
    val mode: String,
)
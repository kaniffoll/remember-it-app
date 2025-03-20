package com.example.rememberit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val text: String,
    val mode: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int  = 0
)
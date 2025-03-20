package com.example.rememberit.ui

import com.example.rememberit.model.Note

data class NoteState (
    val notes: List<Note> = emptyList(),
    val title: String = "",
    val noteText: String = "",
    var showDialog: Boolean = false,
    val id: Int = 0
)
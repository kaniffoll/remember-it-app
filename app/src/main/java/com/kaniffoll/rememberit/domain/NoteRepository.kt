package com.kaniffoll.rememberit.domain

import com.kaniffoll.rememberit.domain.model.Note

interface NoteRepository {

    suspend fun createNote()
    suspend fun deleteNote(note: Note)
    suspend fun getNotes(): List<Note>
    suspend fun updateNote(note: Note)
}
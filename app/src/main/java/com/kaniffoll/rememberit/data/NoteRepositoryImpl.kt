package com.kaniffoll.rememberit.data

import com.kaniffoll.rememberit.data.database.NoteDao
import com.kaniffoll.rememberit.data.model.NoteEntity
import com.kaniffoll.rememberit.domain.NoteRepository
import com.kaniffoll.rememberit.domain.model.Mode
import com.kaniffoll.rememberit.domain.model.ModeSerializer
import com.kaniffoll.rememberit.domain.model.Note
import kotlinx.serialization.json.Json
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val dao: NoteDao) : NoteRepository {
    private val json = Json

    override suspend fun createNote() {
        dao.upsertNote(
            NoteEntity(
                title = "",
                text = "",
                mode = json.encodeToString(ModeSerializer(), Mode.None),
            )
        )
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toNoteEntity())
    }

    override suspend fun getNotes(): List<Note> {
        return dao.getNotes().map { it.toNote() }
    }

    override suspend fun updateNote(note: Note) {
        dao.upsertNote(note.toNoteEntity())
    }

    override suspend fun getNoteById(id: Int): Note {
        return dao.getNoteById(id).toNote()
    }
}
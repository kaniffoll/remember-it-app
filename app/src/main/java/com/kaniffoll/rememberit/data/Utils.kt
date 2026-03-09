package com.kaniffoll.rememberit.data

import com.kaniffoll.rememberit.data.model.NoteEntity
import com.kaniffoll.rememberit.domain.model.Note
import kotlinx.serialization.json.Json

private val json = Json

fun NoteEntity.toNote() = Note(
    id = this.id,
    title = this.title,
    text = this.text,
    mode = json.decodeFromString(this.mode)
)

fun Note.toNoteEntity() = NoteEntity(
    id = this.id,
    title = this.title,
    text = this.text,
    mode = json.encodeToString(this.mode)
)

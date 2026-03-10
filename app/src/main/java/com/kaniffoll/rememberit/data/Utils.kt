package com.kaniffoll.rememberit.data

import android.util.Log
import com.kaniffoll.rememberit.data.model.NoteEntity
import com.kaniffoll.rememberit.domain.model.ModeSerializer
import com.kaniffoll.rememberit.domain.model.Note
import kotlinx.serialization.json.Json

private val json = Json

fun NoteEntity.toNote(): Note
{
    Log.d("TAAFAAAF", this.mode)
    return Note(
        id = this.id,
        title = this.title,
        text = this.text,
        mode = json.decodeFromString(ModeSerializer() ,this.mode)
    )
}

fun Note.toNoteEntity() = NoteEntity(
    id = this.id,
    title = this.title,
    text = this.text,
    mode = json.encodeToString(ModeSerializer(), this.mode)
)

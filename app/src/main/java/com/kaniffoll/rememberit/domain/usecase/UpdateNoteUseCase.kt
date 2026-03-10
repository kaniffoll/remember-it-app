package com.kaniffoll.rememberit.domain.usecase

import com.kaniffoll.rememberit.domain.NoteRepository
import com.kaniffoll.rememberit.domain.model.Note
import jakarta.inject.Inject

class UpdateNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.updateNote(note)
}
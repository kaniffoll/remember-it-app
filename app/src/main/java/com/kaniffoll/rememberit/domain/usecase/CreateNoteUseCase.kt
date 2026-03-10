package com.kaniffoll.rememberit.domain.usecase

import com.kaniffoll.rememberit.domain.NoteRepository
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke() = repository.createNote()
}
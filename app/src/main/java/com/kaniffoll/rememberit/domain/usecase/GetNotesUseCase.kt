package com.kaniffoll.rememberit.domain.usecase

import com.kaniffoll.rememberit.domain.NoteRepository
import jakarta.inject.Inject

class GetNotesUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke() = repository.getNotes()
}
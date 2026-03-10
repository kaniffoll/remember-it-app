package com.kaniffoll.rememberit.domain.usecase

import com.kaniffoll.rememberit.domain.NoteRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(id: Int) = repository.getNoteById(id)
}
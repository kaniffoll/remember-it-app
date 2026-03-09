package com.kaniffoll.rememberit.presentation.ui.screens.list

import com.kaniffoll.rememberit.domain.model.Note

sealed interface NoteListState {
    data object Loading: NoteListState
    data class Success(val notes: List<Note>): NoteListState
}
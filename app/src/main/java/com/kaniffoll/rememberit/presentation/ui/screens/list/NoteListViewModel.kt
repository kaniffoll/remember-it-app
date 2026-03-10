package com.kaniffoll.rememberit.presentation.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaniffoll.rememberit.domain.model.Note
import com.kaniffoll.rememberit.domain.usecase.CreateNoteUseCase
import com.kaniffoll.rememberit.domain.usecase.DeleteNoteUseCase
import com.kaniffoll.rememberit.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteListViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val createNoteUseCase: CreateNoteUseCase
) : ViewModel() {
    private var _notes = MutableStateFlow<NoteListState>(NoteListState.Loading)
    val notes = _notes as StateFlow<NoteListState>

    init {
        viewModelScope.launch {
            refreshNotes()
        }
    }

    suspend fun refreshNotes() {
        _notes.value = NoteListState.Success(getNotesUseCase())
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            _notes.value = NoteListState.Loading
            deleteNoteUseCase(note)
            refreshNotes()
        }
    }

    fun addNote() {
        viewModelScope.launch {
            _notes.value = NoteListState.Loading
            createNoteUseCase()
            refreshNotes()
        }
    }
}
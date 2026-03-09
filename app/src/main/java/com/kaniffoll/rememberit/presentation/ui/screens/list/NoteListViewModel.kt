package com.kaniffoll.rememberit.presentation.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaniffoll.rememberit.domain.NoteRepository
import com.kaniffoll.rememberit.domain.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteListViewModel(
    @Inject
    private val noteRepository: NoteRepository
) : ViewModel() {
    private var _notes = MutableStateFlow<NoteListState>(NoteListState.Loading)
    val notes = _notes as StateFlow<NoteListState>

    init {
        viewModelScope.launch {
            refreshNotes()
        }
    }

    suspend fun refreshNotes() {
        _notes.value = NoteListState.Success(noteRepository.getNotes())
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            _notes.value = NoteListState.Loading
            noteRepository.deleteNote(note)
            refreshNotes()
        }
    }

    fun addNote() {
        viewModelScope.launch {
            _notes.value = NoteListState.Loading
            noteRepository.createNote()
            refreshNotes()
        }
    }
}
package com.kaniffoll.rememberit.presentation.ui.screens.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaniffoll.rememberit.domain.NoteRepository
import com.kaniffoll.rememberit.domain.model.Mode
import com.kaniffoll.rememberit.domain.model.Note
import com.kaniffoll.rememberit.domain.usecase.CreateNotificationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteRepository: NoteRepository,
    private val scheduleNotificationUseCase: CreateNotificationUseCase,
    private val id: Int
): ViewModel() {
    private var _currentNote = MutableStateFlow(NoteState())
    val currentNote = _currentNote as StateFlow<NoteState>

    fun updateText(text: String) {
        _currentNote.value = _currentNote.value.copy(text = text)
    }

    fun updateNoteTitle(title: String) {
        _currentNote.value = _currentNote.value.copy(title = title)
    }

    fun clearNoteText() {
        _currentNote.value = _currentNote.value.copy(text = "")
    }

    fun setAndModeAndUpsertNote(mode: Mode) {
        viewModelScope.launch {
            _currentNote.value = _currentNote.value.copy(mode = mode)
            noteRepository.updateNote(_currentNote.value.toNote())
            scheduleNotificationUseCase(_currentNote.value.text, _currentNote.value.mode)
        }
    }

    private fun NoteState.toNote() =
        Note(
            id = id,
            title = this.title,
            text = this.text,
            mode = this.mode
        )
}


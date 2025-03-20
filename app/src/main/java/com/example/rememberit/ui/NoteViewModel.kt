package com.example.rememberit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rememberit.model.Note
import com.example.rememberit.model.NoteDao
import com.example.rememberit.workers.NotificationScheduler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val dao: NoteDao,
    private val notificationScheduler: NotificationScheduler
): ViewModel() {
    private val _uiState = MutableStateFlow(NoteState())
    val uiState: StateFlow<NoteState> = _uiState.asStateFlow()

    init{
        getNotes()
    }

    fun upsertNote(
        mode: String
    ){
        val id = uiState.value.id
        val note = if (uiState.value.notes.any { it.id == id }) {
            Note(
                title = uiState.value.title,
                text = uiState.value.noteText,
                mode = mode,
                id = id
            )
        } else {
            Note(
                title = uiState.value.title,
                text = uiState.value.noteText,
                mode = mode
            )
        }
        viewModelScope.launch {
            dao.upsertNote(note)
            _uiState.value = _uiState.value.copy(
                notes = uiState.value.notes.plus(note)
            )
            getNotes()

            notificationScheduler.scheduleNotification(note.text, note.mode)
        }
    }

    fun updateId(id:Int){
        _uiState.value = _uiState.value.copy(
            id = id
        )
        val note: Note? = uiState.value.notes.find {it.id == id}
        val title = note?.title ?: ""
        val text = note?.text ?: ""
        updateNoteTitle(title)
        updateNoteText(text)
    }

    private fun getNotes(){
        viewModelScope.launch {
            val notes = dao.getNotes()
            _uiState.value = _uiState.value.copy(
                notes = notes
            )
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            dao.deleteNote(note)
            val updatedNotes = dao.getNotes()
            _uiState.value = _uiState.value.copy(
                notes = updatedNotes
            )
        }
    }

    fun updateNoteTitle(newText:String){
        _uiState.value = _uiState.value.copy(
            title = newText
        )
    }

    fun updateNoteText(newText: String){
        _uiState.value = _uiState.value.copy(
            noteText = newText
        )
    }

    fun clearNoteText(){
        _uiState.value = _uiState.value.copy(
            noteText = ""
        )
    }

    fun updateShowDialog(){
        _uiState.value = _uiState.value.copy(
            showDialog = !uiState.value.showDialog
        )
    }

}
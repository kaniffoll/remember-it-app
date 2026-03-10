package com.kaniffoll.rememberit.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kaniffoll.rememberit.domain.usecase.CreateNoteUseCase
import com.kaniffoll.rememberit.domain.usecase.CreateNotificationUseCase
import com.kaniffoll.rememberit.domain.usecase.DeleteNoteUseCase
import com.kaniffoll.rememberit.domain.usecase.GetNoteByIdUseCase
import com.kaniffoll.rememberit.domain.usecase.GetNotesUseCase
import com.kaniffoll.rememberit.domain.usecase.UpdateNoteUseCase
import com.kaniffoll.rememberit.presentation.ui.screens.list.NoteListViewModel
import com.kaniffoll.rememberit.presentation.ui.screens.note.NoteViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import jakarta.inject.Inject


class NoteListViewModelFactory @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val createNoteUseCase: CreateNoteUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteListViewModel::class.java))
            return NoteListViewModel(
                getNotesUseCase,
                deleteNoteUseCase,
                createNoteUseCase
            ) as T
        throw IllegalArgumentException("Unknown ViewModel Class ${modelClass.name}")
    }
}

class NoteViewModelFactory @AssistedInject constructor(
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val scheduleNotificationUseCase: CreateNotificationUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    @Assisted("id") private val id: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java))
            return NoteViewModel(
                updateNoteUseCase,
                scheduleNotificationUseCase,
                getNoteByIdUseCase,
                id
            ) as T
        throw IllegalArgumentException("Unknown ViewModel Class ${modelClass.name}")
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("id") id: Int): NoteViewModelFactory
    }
}
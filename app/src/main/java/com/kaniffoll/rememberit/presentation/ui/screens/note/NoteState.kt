package com.kaniffoll.rememberit.presentation.ui.screens.note

import com.kaniffoll.rememberit.domain.model.Mode

data class NoteState(
    val title: String = "",
    val text: String = "",
    val mode: Mode = Mode.None
)

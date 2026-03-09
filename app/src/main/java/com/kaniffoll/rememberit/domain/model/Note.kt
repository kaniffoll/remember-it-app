package com.kaniffoll.rememberit.domain.model

data class Note(
    val id: Int,
    val title: String,
    val text: String,
    val mode: Mode
)
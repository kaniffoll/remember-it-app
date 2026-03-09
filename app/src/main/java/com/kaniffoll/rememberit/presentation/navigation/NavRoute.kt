package com.kaniffoll.rememberit.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoute {
    @Serializable
    data object ListRoute : NavRoute

    @Serializable
    data object AboutRoute : NavRoute

    @Serializable
    data object NoteRoute : NavRoute
}
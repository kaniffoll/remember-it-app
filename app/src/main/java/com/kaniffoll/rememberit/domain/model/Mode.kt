package com.kaniffoll.rememberit.domain.model

import kotlinx.serialization.Serializable

@Serializable(with = ModeSerializer::class)
sealed interface Mode {
    @Serializable
    data object Normal: Mode
    @Serializable
    data object Intensive: Mode
}
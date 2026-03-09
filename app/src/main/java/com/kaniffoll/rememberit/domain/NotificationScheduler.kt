package com.kaniffoll.rememberit.domain

import com.kaniffoll.rememberit.domain.model.Mode

interface NotificationScheduler {
    fun scheduleNotification(noteText: String, mode: Mode)
}
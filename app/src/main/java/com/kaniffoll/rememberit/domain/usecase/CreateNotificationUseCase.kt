package com.kaniffoll.rememberit.domain.usecase

import com.kaniffoll.rememberit.domain.NotificationScheduler
import com.kaniffoll.rememberit.domain.model.Mode

class CreateNotificationUseCase(private val notificationScheduler: NotificationScheduler) {
    operator fun invoke(noteText: String, mode: Mode) = notificationScheduler.scheduleNotification(noteText, mode)
}
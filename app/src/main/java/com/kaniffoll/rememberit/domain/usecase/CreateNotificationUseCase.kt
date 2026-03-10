package com.kaniffoll.rememberit.domain.usecase

import com.kaniffoll.rememberit.domain.NotificationScheduler
import com.kaniffoll.rememberit.domain.model.Mode
import jakarta.inject.Inject

class CreateNotificationUseCase @Inject constructor(private val notificationScheduler: NotificationScheduler) {
    operator fun invoke(noteText: String, mode: Mode) = notificationScheduler.scheduleNotification(noteText, mode)
}
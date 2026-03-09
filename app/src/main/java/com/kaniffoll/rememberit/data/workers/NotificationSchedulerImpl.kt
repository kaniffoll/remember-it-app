package com.kaniffoll.rememberit.data.workers

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.kaniffoll.rememberit.data.workers.NotificationResources.INTENSIVE_REPEAT_INTERVAL
import com.kaniffoll.rememberit.data.workers.NotificationResources.NORMAL_REPEAT_INTERVAL
import com.kaniffoll.rememberit.data.workers.NotificationResources.NOTE_TEXT_KEY
import com.kaniffoll.rememberit.data.workers.NotificationResources.WORK_NAME
import com.kaniffoll.rememberit.domain.NotificationScheduler
import com.kaniffoll.rememberit.domain.model.Mode
import java.util.concurrent.TimeUnit

class NotificationSchedulerImpl(private val context: Context) : NotificationScheduler {
    override fun scheduleNotification(noteText: String, mode: Mode) {
        val data = Data.Builder()
            .putString(NOTE_TEXT_KEY, noteText)
            .build()

        val interval =
            if (mode is Mode.Intensive) INTENSIVE_REPEAT_INTERVAL else NORMAL_REPEAT_INTERVAL

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            PeriodicWorkRequestBuilder<NotificationWorker>(
                interval,
                TimeUnit.HOURS
            ).setInputData(data).build()
        )
    }
}
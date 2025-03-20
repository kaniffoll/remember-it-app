package com.example.rememberit.workers

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class NotificationScheduler(private val context: Context) {
    fun scheduleNotification(noteText: String, mode: String){
        val data = Data.Builder()
            .putString("noteText", noteText)
            .build()

        val workRequest = if(mode == "Intensive"){
            PeriodicWorkRequestBuilder<NotificationWorker>(12, TimeUnit.HOURS)
                .setInputData(data)
                .build()
        }
        else{
             PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.DAYS)
                .setInputData(data)
                .build()
        }


        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "NotificationWork",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }
}
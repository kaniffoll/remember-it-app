package com.kaniffoll.rememberit.data.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kaniffoll.rememberit.R
import com.kaniffoll.rememberit.data.workers.NotificationResources.CHANNEL_ID
import com.kaniffoll.rememberit.data.workers.NotificationResources.CHANNEL_NAME
import com.kaniffoll.rememberit.data.workers.NotificationResources.NOTE_TEXT_KEY
import com.kaniffoll.rememberit.data.workers.NotificationResources.NOTIFICATION_TITLE

class NotificationWorker(
    private val context: Context,
    params: WorkerParameters
) : Worker(context, params) {
    override fun doWork(): Result {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = CHANNEL_ID

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(NOTIFICATION_TITLE)
            .setContentText(inputData.getString(NOTE_TEXT_KEY)!!)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(1, notification)
        return Result.success()
    }
}
package com.example.rememberit.workers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.rememberit.R

class NotificationWorker(
    private val context: Context,
    params: WorkerParameters
): Worker(context,params) {
    override fun doWork(): Result {
        val noteText = inputData.getString("noteText")?: "No note available"

        showNotification("Reminder", noteText)
        return Result.success()
    }

    private fun showNotification(title: String, message: String){
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "reminder_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                channelId,
                "Reminder Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(1, notification)
    }
}
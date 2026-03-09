package com.kaniffoll.rememberit.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.kaniffoll.rememberit.data.database.NoteDatabase
import com.kaniffoll.rememberit.data.workers.NotificationSchedulerImpl
import com.kaniffoll.rememberit.presentation.navigation.RememberItApp
import com.kaniffoll.rememberit.presentation.theme.RememberItTheme

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            name = "notes"
        ).build()
    }

    private val notificationScheduler by lazy {
        NotificationSchedulerImpl(applicationContext)
    }

    private val viewModel by viewModels<NoteViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NoteViewModel(db.dao, notificationScheduler) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RememberItTheme {
                Surface(
                    modifier = Modifier.Companion.fillMaxSize()
                ) {
                    RememberItApp(noteViewModel = viewModel)
                }
            }
        }
    }
}
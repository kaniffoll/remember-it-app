package com.kaniffoll.rememberit.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.kaniffoll.rememberit.di.AppComponentProvider
import com.kaniffoll.rememberit.di.NoteListViewModelFactory
import com.kaniffoll.rememberit.di.NoteViewModelFactory
import com.kaniffoll.rememberit.presentation.navigation.Navigation
import com.kaniffoll.rememberit.presentation.theme.RememberItTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var noteListViewModelFactory: NoteListViewModelFactory
    @Inject
    lateinit var noteViewModelFactory: NoteViewModelFactory.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AppComponentProvider).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RememberItTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Navigation(
                        noteListViewModelFactory,
                        noteViewModelFactory
                    )
                }
            }
        }
    }
}
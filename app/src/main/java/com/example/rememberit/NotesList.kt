package com.example.rememberit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rememberit.model.Note
import com.example.rememberit.ui.NoteViewModel

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesList(
    noteViewModel: NoteViewModel,
    modifier: Modifier,
    onNextButtonClicked: () -> Unit,
    aboutButtonClicked: () -> Unit,
    onCardClicked: (Int) -> Unit
) {
        val noteUiState by noteViewModel.uiState.collectAsState()
        Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {aboutButtonClicked()}) {
                        Icon(
                            painter = painterResource(R.drawable.info_24dp),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    scrolledContainerColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(noteUiState.notes){ note->
                    NoteCard(
                        note = note,
                        modifier = Modifier,
                        onDeleteButtonClicked = {noteViewModel.deleteNote(note)},
                        onCardClicked = {onCardClicked(note.id)}
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    )
    Box(
        modifier = Modifier.fillMaxSize().padding(end = 16.dp, bottom = 48.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Button(
            onClick = {
                onNextButtonClicked()
            },
            modifier = Modifier.size(64.dp).clip(RoundedCornerShape(size = 24.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer),
            shape = RectangleShape
        ) {
            Text(text = stringResource(R.string.plus), style = MaterialTheme.typography.headlineLarge)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun NotesPreview() {
//    RememberItTheme {
//        NotesList(
//            modifier = Modifier,
//            onNextButtonClicked = {},
//            aboutButtonClicked = {}
//        )
//    }
//}
package com.kaniffoll.rememberit.presentation.ui.screens.list

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.kaniffoll.rememberit.R
import com.kaniffoll.rememberit.presentation.ui.components.NoteCard
import com.kaniffoll.rememberit.presentation.ui.res.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(
    onAboutClick: () -> Unit,
    onCardClick: (Int) -> Unit
) {
    val viewModel: NoteListViewModel
    val notesState by viewModel.notes.collectAsState()

    Scaffold(
        topBar = { TopAppBar { onAboutClick() } },
        content = { innerPadding ->
            when (notesState) {
                is NoteListState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is NoteListState.Success -> {
                    LazyColumn(
                        contentPadding = innerPadding,
                        verticalArrangement = Arrangement.spacedBy(Dimens.MEDIUM_PADDING),
                    ) {
                        items((notesState as NoteListState.Success).notes) { note ->
                            NoteCard(
                                note = note,
                                modifier = Modifier,
                                onDeleteButtonClicked = { viewModel.deleteNote(note) },
                                onCardClicked = { onCardClick(note.id) }
                            )
                        }
                    }
                }
            }
        },
    )
    FAB { viewModel.addNote() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(onClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge
            )
        },
        navigationIcon = {
            IconButton(onClick) {
                Icon(
                    painter = painterResource(R.drawable.info_24dp),
                    contentDescription = null,
                    modifier = Modifier.size(Dimens.ICON_SIZE)
                )
            }
        }
    )
}

@Composable
private fun FAB(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = Dimens.LARGE_PADDING, bottom = Dimens.SPACE_UNDER_FAB),
        contentAlignment = Alignment.BottomEnd
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .size(Dimens.FAB_SIZE)
                .clip(RoundedCornerShape(Dimens.FAB_ROUNDED_SHAPE)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer),
            shape = RectangleShape
        ) {
            Text(
                text = stringResource(R.string.plus),
                style = MaterialTheme.typography.headlineLarge
            )
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
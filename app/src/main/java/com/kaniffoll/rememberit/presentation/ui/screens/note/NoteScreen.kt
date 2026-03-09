package com.kaniffoll.rememberit.presentation.ui.screens.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.kaniffoll.rememberit.R
import com.kaniffoll.rememberit.presentation.ui.res.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    onBack: () -> Unit,
) {
    val lineHeightPx = 130f
    val lineHeightSp: TextUnit = with(LocalDensity.current) { lineHeightPx.toSp() }
    var showDialog by remember { mutableStateOf(false) }
    val viewModel: NoteViewModel
    val noteState by viewModel.currentNote.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(noteState = noteState, viewModel = viewModel, onBack) {
                showDialog = !showDialog
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = Dimens.SMALL_PADDING)
            ) {
                OutlinedTextField(
                    value = noteState.text,
                    onValueChange = { newText ->
                        viewModel.updateText(newText)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = Dimens.MEDIUM_PADDING)
                        .drawBehind {
                            val lineColor = Color.LightGray
                            var y = lineHeightPx
                            while (y < size.height) {
                                drawLine(
                                    color = lineColor,
                                    start = Offset(15f, y),
                                    end = Offset(size.width - 15f, y),
                                    strokeWidth = 2f
                                )
                                y += lineHeightPx
                            }
                        },
                    shape = RoundedCornerShape(Dimens.LARGE_PADDING),
                    textStyle = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 25.sp,
                        lineHeight = lineHeightSp
                    ),
                    singleLine = false,
                )
            }
        },
    )
    if (showDialog) {
        NoteDialog(onHide = { showDialog = !showDialog }, onBack = onBack)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    noteState: NoteState,
    viewModel: NoteViewModel,
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.NOTE_TITLE_HEIGHT),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = noteState.title,
                    onValueChange = { newText ->
                        viewModel.updateNoteTitle(newText)
                    },
                    placeholder = @Composable {
                        Text(
                            text = stringResource(R.string.title),
                            style = MaterialTheme.typography.headlineLarge
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = MaterialTheme.typography.headlineLarge.copy(color = Color.White),
                    singleLine = true,
                )
            }
        },
        navigationIcon = {
            IconItem(R.drawable.arrow_back) { onBack() }
        },
        actions = {
            IconItem(R.drawable.delete) { viewModel.clearNoteText() }
            IconItem(R.drawable.check_circle) { onSave() }
        }
    )
}

@Composable
private fun IconItem(id: Int, onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            painter = painterResource(id),
            contentDescription = null,
            modifier = Modifier.size(Dimens.ICON_SIZE)
        )
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun NoteScreenPreview() {
//    val placeholder: Any? = null
//    RememberItTheme {
//        NoteFun(
//            modifier = Modifier,
//            noteViewModel = placeholder as NoteViewModel,
//            onBackButtonClicked = {}
//        )
//    }
//}
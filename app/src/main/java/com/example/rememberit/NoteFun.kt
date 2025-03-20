package com.example.rememberit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.rememberit.ui.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteFun(
    modifier: Modifier,
    noteViewModel: NoteViewModel,
    onBackButtonClicked: ()-> Unit,
) {
    val noteUiState by noteViewModel.uiState.collectAsState()
    val lineHeightPx = 130f
    val lineHeightSp: TextUnit = with(LocalDensity.current) { lineHeightPx.toSp() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        BasicTextField(
                            value = noteUiState.title,
                            onValueChange = { newText ->
                                noteViewModel.updateNoteTitle(newText)
                            },
                            decorationBox = { innerTextField ->
                                Box(modifier = Modifier) {
                                    if (noteUiState.title.isEmpty()) {
                                        Text(
                                            text = stringResource(R.string.title),
                                            style = MaterialTheme.typography.headlineLarge
                                        )
                                    }
                                    innerTextField()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            textStyle = MaterialTheme.typography.headlineLarge.copy(color = Color.White),
                            singleLine = true,
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {onBackButtonClicked()}) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {noteViewModel.clearNoteText()}) {
                        Icon(
                            painter = painterResource(R.drawable.delete),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    IconButton(onClick = {noteViewModel.updateShowDialog()}) {
                        Icon(
                            painter = painterResource(R.drawable.check_circle),
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
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(start = 5.dp, end = 5.dp)
            ) {
                Spacer(modifier = Modifier.height(7.dp))
                OutlinedTextField(
                    value = noteUiState.noteText,
                    onValueChange = { newText ->
                        noteViewModel.updateNoteText(newText)
                    },
                    modifier = Modifier
                        .fillMaxSize()
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
                    shape = RoundedCornerShape(15.dp),
                    textStyle = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 25.sp,
                        lineHeight = lineHeightSp
                    ),
                    singleLine = false,
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    )
    if (noteUiState.showDialog){
        Dialog(
            onDismissRequest = {noteViewModel.updateShowDialog()},
            content = {
                Card(
                    modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                    colors = CardColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = stringResource(R.string.memorize_mode),
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center,
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                            OutlinedButton(
                                onClick = {
                                    noteViewModel.updateShowDialog()
                                    noteViewModel.upsertNote("Medium")
                                    onBackButtonClicked()
                                }
                            ){
                                Text(
                                    text = stringResource(R.string.medium_mode),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    maxLines = 1
                                )
                            }
                            OutlinedButton(
                                onClick = {
                                    noteViewModel.updateShowDialog()
                                    noteViewModel.upsertNote("Intensive")
                                    onBackButtonClicked()
                                }
                            ){
                                Text(
                                    text = stringResource(R.string.intensive_mode),
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    maxLines = 1
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun NotePreview(){
//    RememberItTheme {
//        NoteFun(
//            modifier = Modifier,
//            onBackButtonClicked = {}
//        )
//    }
//}
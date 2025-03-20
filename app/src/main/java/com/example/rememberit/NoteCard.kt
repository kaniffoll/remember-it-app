package com.example.rememberit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rememberit.model.Note

@Composable
fun NoteCard(
        note: Note, modifier: Modifier,
        onDeleteButtonClicked: ()->Unit,
        onCardClicked: () -> Unit
    ){
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(start = 5.dp, end = 5.dp)
            .clickable { onCardClicked() },
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ){
        Row(
            modifier
                .fillMaxWidth()
                .height(80.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(start = 10.dp)
                )
                if(note.mode == stringResource(R.string.intensive_mode))
                Icon(
                    painter = painterResource(R.drawable.bolt_24dp),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
            IconButton(onClick = onDeleteButtonClicked) {
                Icon(
                    painter = painterResource(R.drawable.delete),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun NotePreview(){
    NoteCard(note = Note(title = "Hello", text = "Text", mode = "Intensive", id = 0), modifier = Modifier, {}, {})
}
package com.kaniffoll.rememberit.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kaniffoll.rememberit.R
import com.kaniffoll.rememberit.domain.model.Mode
import com.kaniffoll.rememberit.domain.model.Note
import com.kaniffoll.rememberit.presentation.ui.res.Dimens

@Composable
fun NoteCard(
    note: Note,
    modifier: Modifier,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .clip(RoundedCornerShape(Dimens.LARGE_PADDING))
            .padding(horizontal = Dimens.SMALL_PADDING)
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .height(Dimens.NOTE_CARD_HEIGHT),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(Dimens.MEDIUM_PADDING)
                )
                if (note.mode == Mode.Intensive)
                    Icon(
                        painter = painterResource(R.drawable.bolt_24dp),
                        contentDescription = null,
                        modifier = Modifier.size(Dimens.ICON_SIZE)
                    )
            }
            IconButton(onClick = onDelete) {
                Icon(
                    painter = painterResource(R.drawable.delete),
                    contentDescription = null,
                    modifier = Modifier.size(Dimens.ICON_SIZE)
                )
            }
        }
    }
}

@Preview
@Composable
fun NotePreview() {
    NoteCard(
        note = Note(title = "Hello", text = "Text", mode = Mode.Intensive, id = 0),
        modifier = Modifier,
        {},
        {})
}
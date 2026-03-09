package com.kaniffoll.rememberit.presentation.ui.screens.note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.kaniffoll.rememberit.R
import com.kaniffoll.rememberit.domain.model.Mode
import com.kaniffoll.rememberit.presentation.ui.res.Dimens

@Composable
fun NoteDialog(
    viewModel: NoteViewModel,
    onHide: () -> Unit,
    onBack: () -> Unit
) {

    Dialog(
        onDismissRequest = onHide,
        content = {
            Card(
                modifier = Modifier.clip(RoundedCornerShape(Dimens.LARGE_PADDING)),
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.memorize_mode),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Dimens.MEDIUM_PADDING),
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = Dimens.MEDIUM_PADDING),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        DialogButton(
                            mode = Mode.Normal,
                            id = R.string.normal_mode,
                            viewModel = viewModel,
                            onBack = onBack
                        )

                        DialogButton(
                            mode = Mode.Intensive,
                            id = R.string.intensive_mode,
                            viewModel = viewModel,
                            onBack = onBack
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun DialogButton(
    mode: Mode,
    id: Int,
    viewModel: NoteViewModel,
    onBack: () -> Unit
) {
    OutlinedButton(
        onClick = {
            viewModel.setAndModeAndUpsertNote(mode)
            onBack()
        }
    ) {
        Text(
            text = stringResource(id),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 1
        )
    }
}
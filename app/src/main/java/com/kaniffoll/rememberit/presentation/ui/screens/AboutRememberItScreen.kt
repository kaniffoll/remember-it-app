package com.kaniffoll.rememberit.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kaniffoll.rememberit.R
import com.kaniffoll.rememberit.presentation.theme.RememberItTheme
import com.kaniffoll.rememberit.presentation.ui.res.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutRememberItScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { TopBar { onBack() } },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = Dimens.SMALL_PADDING)
                    .verticalScroll(rememberScrollState())
            ) {
                Textblock(R.string.how_to_use_title, MaterialTheme.typography.titleLarge)
                Textblock(R.string.how_to_use_text, MaterialTheme.typography.bodyLarge)
                Textblock(R.string.method_description_title, MaterialTheme.typography.titleLarge)
                Textblock(R.string.method_description_text, MaterialTheme.typography.bodyLarge)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(onClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.about_app_top_bar),
                style = MaterialTheme.typography.headlineLarge
            )
        },
        navigationIcon = {
            IconButton(onClick) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = null,
                    modifier = Modifier.size(Dimens.ICON_SIZE)
                )
            }
        }
    )
}

@Composable
private fun Textblock(id: Int, style: TextStyle) {
    Text(
        text = stringResource(id),
        style = style,
        textAlign = TextAlign.Justify,
    )
}

@Preview(showBackground = true)
@Composable
fun ARIPreview() {
    RememberItTheme {
        AboutRememberItScreen(
            onBack = {}
        )
    }
}
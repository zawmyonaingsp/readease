package com.sevenpeaks.zawmyonaing.readease.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingLarge
import com.sevenpeaks.zawmyonaing.readease.ui.widgets.ThemedPreview
import com.sevenpeaks.zawmyonaing.readease.utils.android.compose.preview.UiModePreviews

@Composable
fun ScreenHeroText(
    modifier: Modifier,
    message: String,
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(
                    bottomStart = 24.dp,
                    bottomEnd = 24.dp
                )
            )
            .padding(spacingLarge),
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@UiModePreviews
@Composable
private fun ScreenHeroTextPreview() {
    ThemedPreview {
        Column(modifier = Modifier.fillMaxSize()) {
            ScreenHeroText(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                message = "Sample Hero Message"
            )
        }
    }
}
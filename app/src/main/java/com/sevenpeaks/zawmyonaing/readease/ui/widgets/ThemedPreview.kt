package com.sevenpeaks.zawmyonaing.readease.ui.widgets

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sevenpeaks.zawmyonaing.readease.ui.theme.ReadEaseTheme

@Composable
fun ThemedPreview(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    ReadEaseTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}

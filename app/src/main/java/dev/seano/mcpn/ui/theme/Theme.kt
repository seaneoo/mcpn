package dev.seano.mcpn.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Theme(content: @Composable () -> Unit) {
    val colorScheme = if (isSystemInDarkTheme()) darkScheme else lightScheme

    MaterialTheme(colorScheme = colorScheme) {
        Surface(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
                content()
            }
    }
}

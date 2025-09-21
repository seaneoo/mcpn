package dev.seano.mcpn.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Composable
fun rememberAppState(): AppState {
    return remember { AppState() }
}

@Stable class AppState {}

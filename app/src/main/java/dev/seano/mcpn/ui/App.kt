package dev.seano.mcpn.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.seano.mcpn.ui.nav.AppNavHost
import dev.seano.mcpn.ui.nav.Destination
import dev.seano.mcpn.ui.theme.Theme

@Composable
fun App(appState: AppState = rememberAppState()) {
    Theme {
        Scaffold { padding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(padding).consumeWindowInsets(padding)) {
                    AppNavHost(appState, Destination.Home)
                }
        }
    }
}

package dev.seano.mcpn.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.seano.mcpn.ui.AppState
import dev.seano.mcpn.ui.screens.HomeScreen

@Composable
fun AppNavHost(appState: AppState, startDestination: Destination, modifier: Modifier = Modifier) {
    NavHost(
        navController = appState.navHostController,
        startDestination = startDestination,
        modifier = modifier) {
            composable<Destination.Home> { HomeScreen() }
        }
}

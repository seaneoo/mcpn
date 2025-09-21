package dev.seano.mcpn.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAppState(navHostController: NavHostController = rememberNavController()): AppState {
    return remember(navHostController) { AppState(navHostController) }
}

@Stable class AppState(val navHostController: NavHostController)

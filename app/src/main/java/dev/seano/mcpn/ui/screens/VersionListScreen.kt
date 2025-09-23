package dev.seano.mcpn.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.seano.mcpn.network.NetworkResponse
import dev.seano.mcpn.ui.viewmodel.VersionListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun VersionListScreen() {
    val viewModel = koinViewModel<VersionListViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.loadData() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            when (val response = state.value.versionManifestResponse) {
                is NetworkResponse.Failure -> {
                    Text(response.error.localizedMessage ?: "Unknown error")
                }
                is NetworkResponse.Loading -> {
                    CircularProgressIndicator()
                }
                is NetworkResponse.Success -> {
                    Text("Latest release: ${response.data.latest.release}")
                    Text("Latest snapshot: ${response.data.latest.snapshot}")
                }
            }
        }
}

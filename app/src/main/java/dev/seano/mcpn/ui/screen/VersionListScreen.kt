package dev.seano.mcpn.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.seano.mcpn.data.model.VersionManifest
import dev.seano.mcpn.data.model.VersionManifestVersion
import dev.seano.mcpn.network.NetworkResponse
import dev.seano.mcpn.ui.viewmodel.VersionListViewModel
import dev.seano.mcpn.util.DateUtils
import org.koin.androidx.compose.koinViewModel

@Composable
fun VersionListScreen() {
    val viewModel = koinViewModel<VersionListViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.loadData() }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val response = state.value.versionManifestResponse) {
            is NetworkResponse.Failure -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(response.error.localizedMessage ?: "Unknown error")
                    }
            }
            is NetworkResponse.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                    }
            }
            is NetworkResponse.Success -> {
                VersionList(response.data)
            }
        }
    }
}

@Composable
internal fun VersionList(versionManifest: VersionManifest) {
    val versions = versionManifest.versions
    LazyColumn {
        itemsIndexed(versions) { index, version ->
            VersionListItem(version)
            if (index < versions.size) HorizontalDivider()
        }
    }
}

@Composable
internal fun VersionListItem(version: VersionManifestVersion) {
    Column(
        modifier =
            Modifier.fillMaxWidth()
                .clickable(
                    onClick = { /* TODO */ },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple())
                .padding(16.dp)) {
            Text("${version.id} - ${version.type}")
            Text(DateUtils.formatDateTimeWithZone(version.releaseTime))
        }
}

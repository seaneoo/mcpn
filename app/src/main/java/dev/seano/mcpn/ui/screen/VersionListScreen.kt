package dev.seano.mcpn.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import dev.seano.mcpn.ui.viewmodel.VersionFilter
import dev.seano.mcpn.ui.viewmodel.VersionListViewModel
import dev.seano.mcpn.util.DateUtils
import org.koin.androidx.compose.koinViewModel

@Composable
fun VersionListScreen() {
    val viewModel = koinViewModel<VersionListViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()

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
                VersionList(
                    response.data,
                    lazyListState,
                    state.value.versionFilters,
                    toggleFilter = { viewModel.toggleFilter(it) })
            }
        }
    }
}

@Composable
internal fun VersionFilterChips(
    activeFilters: Set<VersionFilter>,
    toggleFilter: (VersionFilter) -> Unit
) {
    Row(
        modifier =
            Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background).padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {
            VersionFilter.entries.forEach { it.Chip(toggleFilter, activeFilters) }
        }
}

@Composable
internal fun VersionList(
    versionManifest: VersionManifest,
    lazyListState: LazyListState,
    activeFilters: Set<VersionFilter>,
    toggleFilter: (VersionFilter) -> Unit
) {
    val versions = versionManifest.versions
    val filteredVersions =
        versions.filter { version ->
            activeFilters.isEmpty() ||
                when (version.type) {
                    "release" -> VersionFilter.RELEASE in activeFilters
                    "snapshot" -> VersionFilter.SNAPSHOT in activeFilters
                    "old_beta" -> VersionFilter.OLD_BETA in activeFilters
                    "old_alpha" -> VersionFilter.OLD_ALPHA in activeFilters
                    else -> false
                }
        }

    LazyColumn(state = lazyListState) {
        stickyHeader {
            VersionFilterChips(activeFilters, toggleFilter)
            HorizontalDivider()
        }

        itemsIndexed(filteredVersions) { index, version ->
            VersionListItem(version)
            if (index < filteredVersions.size) HorizontalDivider()
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

package dev.seano.mcpn.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seano.mcpn.data.model.VersionManifest
import dev.seano.mcpn.network.ApiService
import dev.seano.mcpn.network.NetworkResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class VersionListViewModel(private val apiService: ApiService) : ViewModel() {
    data class UiState(
        val versionManifestResponse: NetworkResponse<VersionManifest> = NetworkResponse.Loading()
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private suspend fun fetchData() {
        _uiState.update { UiState(versionManifestResponse = NetworkResponse.Loading()) }
        val versionManifest = apiService.fetchVersionManifest()
        _uiState.update { UiState(versionManifestResponse = versionManifest) }
    }

    fun loadData() {
        viewModelScope.launch { fetchData() }
    }
}

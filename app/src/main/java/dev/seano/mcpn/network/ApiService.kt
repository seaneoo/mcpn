package dev.seano.mcpn.network

import dev.seano.mcpn.data.model.Version
import dev.seano.mcpn.data.model.VersionManifest
import dev.seano.mcpn.data.model.VersionManifestVersion
import org.koin.core.annotation.Single

@Single
class ApiService(private val networkService: NetworkService) {
    private object Endpoints {
        const val VERSION_MANIFEST =
            "https://piston-meta.mojang.com/mc/game/version_manifest_v2.json"
    }

    suspend fun fetchVersionManifest(): NetworkResponse<VersionManifest> =
        networkService.get<VersionManifest>(Endpoints.VERSION_MANIFEST)

    suspend fun fetchVersion(version: VersionManifestVersion): NetworkResponse<Version> =
        networkService.get<Version>(version.url)
}

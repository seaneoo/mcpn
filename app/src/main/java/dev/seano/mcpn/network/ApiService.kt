package dev.seano.mcpn.network

import dev.seano.mcpn.data.model.Version
import dev.seano.mcpn.data.model.VersionManifest

class ApiService(private val networkService: NetworkService) {
    private object Endpoints {
        const val VERSION_MANIFEST =
            "https://piston-meta.mojang.com/mc/game/version_manifest_v2.json"
    }

    suspend fun fetchVersionManifest() =
        networkService.get<VersionManifest>(Endpoints.VERSION_MANIFEST)

    suspend fun fetchVersion(version: VersionManifest.Version) =
        networkService.get<Version>(version.url)
}

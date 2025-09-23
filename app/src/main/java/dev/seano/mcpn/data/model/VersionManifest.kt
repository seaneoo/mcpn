package dev.seano.mcpn.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionManifest(
    @SerialName("latest") val latest: VersionManifestLatest,
    @SerialName("versions") val versions: List<VersionManifestVersion>
)

@Serializable
data class VersionManifestLatest(
    @SerialName("release") val release: String,
    @SerialName("snapshot") val snapshot: String
)

@Serializable
data class VersionManifestVersion(
    @SerialName("complianceLevel") val complianceLevel: Int,
    @SerialName("id") val id: String,
    @SerialName("releaseTime") val releaseTime: String,
    @SerialName("sha1") val sha1: String,
    @SerialName("time") val time: String,
    @SerialName("type") val type: String,
    @SerialName("url") val url: String
)

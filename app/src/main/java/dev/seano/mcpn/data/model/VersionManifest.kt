package dev.seano.mcpn.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionManifest(
    @SerialName("latest") val latest: Latest,
    @SerialName("versions") val versions: List<Version>
) {
    @Serializable
    data class Latest(
        @SerialName("release") val release: String,
        @SerialName("snapshot") val snapshot: String
    )

    @Serializable
    data class Version(
        @SerialName("complianceLevel") val complianceLevel: Int,
        @SerialName("id") val id: String,
        @SerialName("releaseTime") val releaseTime: String,
        @SerialName("sha1") val sha1: String,
        @SerialName("time") val time: String,
        @SerialName("type") val type: String,
        @SerialName("url") val url: String
    )
}

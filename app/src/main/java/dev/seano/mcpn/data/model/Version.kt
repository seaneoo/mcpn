package dev.seano.mcpn.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Version(
    @SerialName("assetIndex") val assetIndex: AssetIndex,
    @SerialName("assets") val assets: String,
    @SerialName("complianceLevel") val complianceLevel: Int,
    @SerialName("downloads") val downloads: Downloads,
    @SerialName("id") val id: String,
    @SerialName("javaVersion") val javaVersion: JavaVersion,
    @SerialName("libraries") val libraries: List<Library>,
    @SerialName("logging") val logging: Logging,
    @SerialName("mainClass") val mainClass: String,
    @SerialName("minimumLauncherVersion") val minimumLauncherVersion: Int,
    @SerialName("releaseTime") val releaseTime: String,
    @SerialName("time") val time: String,
    @SerialName("type") val type: String
) {
    @Serializable
    data class AssetIndex(
        @SerialName("id") val id: String,
        @SerialName("sha1") val sha1: String,
        @SerialName("size") val size: Int,
        @SerialName("totalSize") val totalSize: Int,
        @SerialName("url") val url: String
    )

    @Serializable
    data class Downloads(
        @SerialName("client") val client: Jar,
        @SerialName("client_mappings") val clientMappings: Mappings
    ) {
        @Serializable
        data class Jar(
            @SerialName("sha1") val sha1: String,
            @SerialName("size") val size: Int,
            @SerialName("url") val url: String
        )

        @Serializable
        data class Mappings(
            @SerialName("sha1") val sha1: String,
            @SerialName("size") val size: Int,
            @SerialName("url") val url: String
        )
    }

    @Serializable
    data class JavaVersion(
        @SerialName("component") val component: String,
        @SerialName("majorVersion") val majorVersion: Int
    )

    @Serializable
    data class Library(
        @SerialName("downloads") val downloads: Downloads,
        @SerialName("name") val name: String,
        @SerialName("rules") val rules: List<Rule>
    ) {
        @Serializable
        data class Downloads(@SerialName("artifact") val artifact: Artifact) {
            @Serializable
            data class Artifact(
                @SerialName("path") val path: String,
                @SerialName("sha1") val sha1: String,
                @SerialName("size") val size: Int,
                @SerialName("url") val url: String
            )
        }

        @Serializable
        data class Rule(@SerialName("action") val action: String, @SerialName("os") val os: Os) {
            @Serializable data class Os(@SerialName("name") val name: String)
        }
    }

    @Serializable
    data class Logging(@SerialName("client") val client: Client) {
        @Serializable
        data class Client(
            @SerialName("argument") val argument: String,
            @SerialName("file") val `file`: File,
            @SerialName("type") val type: String
        ) {
            @Serializable
            data class File(
                @SerialName("id") val id: String,
                @SerialName("sha1") val sha1: String,
                @SerialName("size") val size: Int,
                @SerialName("url") val url: String
            )
        }
    }
}

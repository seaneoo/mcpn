package dev.seano.mcpn.ui.nav

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable data object VersionList : Destination
}

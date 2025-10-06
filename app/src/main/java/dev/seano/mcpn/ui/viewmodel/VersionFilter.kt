package dev.seano.mcpn.ui.viewmodel

import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.seano.mcpn.R

enum class VersionFilter(val stringId: Int) {
    RELEASE(R.string.releases),
    SNAPSHOT(R.string.snapshots),
    OLD_BETA(R.string.old_betas),
    OLD_ALPHA(R.string.old_alphas);

    @Composable
    fun Chip(toggle: (VersionFilter) -> Unit, active: Set<VersionFilter>) {
        FilterChip(
            onClick = { toggle(this) },
            selected = this in active,
            label = { Text(stringResource(this.stringId)) })
    }
}

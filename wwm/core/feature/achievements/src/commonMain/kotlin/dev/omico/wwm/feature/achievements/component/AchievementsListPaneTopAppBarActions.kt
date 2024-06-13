/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.omico.wwm.feature.achievements.AchievementsUiEvent
import dev.omico.wwm.feature.achievements.AchievementsUiState
import dev.omico.wwm.feature.achievements.platform.exportData
import dev.omico.wwm.feature.achievements.platform.importData
import dev.omico.wwm.resources.WwmIcons
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun AchievementsListPaneTopAppBarActions(
    state: AchievementsUiState,
    categoriesToggleState: AchievementCategoriesToggleState,
) {
    var isImportExportDialogShown by remember { mutableStateOf(false) }
    ImportExportDialog(
        isImportExportDialogShown = isImportExportDialogShown,
        onDismissRequest = { isImportExportDialogShown = false },
        onImport = { importData { json -> state.eventSink(AchievementsUiEvent.ImportData(json)) } },
        onExport = { state.eventSink(AchievementsUiEvent.ExportData(::exportData)) },
    )
    IconButton(
        onClick = { isImportExportDialogShown = true },
        content = {
            Icon(
                painter = painterResource(resource = WwmIcons.ImportExport),
                contentDescription = "Import/Export",
            )
        },
    )
    IconButton(
        onClick = categoriesToggleState::collapseAll,
        content = {
            Icon(
                painter = painterResource(resource = WwmIcons.CollapseAll),
                contentDescription = "Collapse all",
            )
        },
    )
    IconButton(
        onClick = categoriesToggleState::expandAll,
        content = {
            Icon(
                painter = painterResource(resource = WwmIcons.ExpandAll),
                contentDescription = "Expand all",
            )
        },
    )
}

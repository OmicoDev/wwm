/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.omico.wwm.resources.WwmIcons
import dev.omico.wwm.ui.DialogWithIconButton
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun ImportExportDialog(
    onImport: () -> Unit,
    onExport: () -> Unit,
) {
    DialogWithIconButton(
        painter = painterResource(resource = WwmIcons.ImportExport),
        title = { Text(text = "Import/Export") },
        content = {
            Row(horizontalArrangement = Arrangement.spacedBy(space = 8.dp)) {
                ExtendedFloatingActionButton(
                    text = { Text(text = "Import") },
                    icon = {
                        Icon(
                            painter = painterResource(resource = WwmIcons.Import),
                            contentDescription = "Import",
                        )
                    },
                    onClick = onImport,
                )
                ExtendedFloatingActionButton(
                    text = { Text(text = "Export") },
                    icon = {
                        Icon(
                            painter = painterResource(resource = WwmIcons.Export),
                            contentDescription = "Export",
                        )
                    },
                    onClick = onExport,
                )
            }
        },
    )
}

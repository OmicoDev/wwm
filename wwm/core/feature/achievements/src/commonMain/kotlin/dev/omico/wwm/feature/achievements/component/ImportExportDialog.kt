/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.omico.wwm.resources.WwmIcons
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun ImportExportDialog(
    isImportExportDialogShown: Boolean,
    onDismissRequest: () -> Unit,
    onImport: () -> Unit,
    onExport: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(visible = isImportExportDialogShown) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            confirmButton = {},
            modifier = modifier.animateEnterExit(
                enter = fadeIn(animationSpec = tween()),
                exit = fadeOut(animationSpec = tween()),
            ),
            dismissButton = {
                TextButton(
                    onClick = onDismissRequest,
                    content = { Text(text = "Close") },
                )
            },
            title = { Text(text = "Import/Export") },
            text = {
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
}

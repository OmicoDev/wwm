/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DialogWithIconButton(
    painter: Painter,
    title: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    var isDialogShown by remember { mutableStateOf(false) }
    val onDismissRequest: () -> Unit = { isDialogShown = false }
    IconButton(
        onClick = { isDialogShown = true },
        content = {
            Icon(
                painter = painter,
                contentDescription = null,
            )
        },
    )
    AnimatedVisibility(visible = isDialogShown) {
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
            title = title,
            text = content,
        )
    }
}

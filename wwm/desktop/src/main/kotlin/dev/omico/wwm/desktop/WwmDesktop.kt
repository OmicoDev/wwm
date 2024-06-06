/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
@file:JvmName("WwmDesktop")

package dev.omico.wwm.desktop

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import dev.omico.wwm.application.WwmApp

fun main() {
    singleWindowApplication(
        state = WindowState(
            position = WindowPosition(alignment = Alignment.Center),
            size = DpSize(
                width = 1200.dp,
                height = 800.dp,
            ),
        ),
        title = "",
        icon = ColorPainter(color = Color.Transparent),
        content = { WwmApp() },
    )
}

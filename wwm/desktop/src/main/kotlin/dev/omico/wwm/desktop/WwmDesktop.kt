/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
@file:JvmName("WwmDesktop")

package dev.omico.wwm.desktop

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import dev.omico.wwm.application.WwmApp
import dev.omico.wwm.resources.WwmResources
import org.jetbrains.compose.resources.painterResource

fun main() {
    application {
        Window(
            onCloseRequest = ::exitApplication,
            state = WindowState(
                position = WindowPosition(alignment = Alignment.Center),
                size = DpSize(
                    width = 1200.dp,
                    height = 800.dp,
                ),
            ),
            title = "Wuthering Waves Manager",
            icon = painterResource(resource = WwmResources.WutheringWavesIcon),
            content = { WwmApp() },
        )
    }
}

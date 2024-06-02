/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.web

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import dev.omico.wwm.application.WwmApp

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow(title = "Wuthering Waves Manager") {
        WwmApp()
    }
}

/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
@file:JvmName("WwmDesktop")

package dev.omico.wwm.desktop

import androidx.compose.ui.window.singleWindowApplication
import dev.omico.wwm.application.WwmApp

fun main() {
    singleWindowApplication(
        title = "",
        content = { WwmApp() },
    )
}

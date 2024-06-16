/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.ui.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import dev.omico.wwm.resources.model.game.WwLocale

val LocalWwmLocale: ProvidableCompositionLocal<WwLocale> =
    staticCompositionLocalOf { error("No LocalWwmLocale provided") }

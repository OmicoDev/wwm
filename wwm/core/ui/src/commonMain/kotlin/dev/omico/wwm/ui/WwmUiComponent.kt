/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.ui

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.slack.circuit.foundation.Circuit
import dev.omico.wwm.data.WwmDataComponent

interface WwmUiComponent : WwmDataComponent {
    val circuit: Circuit
    fun provideCircuit(): Circuit
}

val LocalWwmUiComponent: ProvidableCompositionLocal<WwmUiComponent> =
    staticCompositionLocalOf { error("No WwmComponent provided") }

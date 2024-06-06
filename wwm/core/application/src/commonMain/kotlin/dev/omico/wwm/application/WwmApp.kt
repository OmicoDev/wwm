/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.application

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dev.omico.wwm.feature.achievements.AchievementsScreen
import dev.omico.wwm.ui.WwmContainer

@Composable
fun WwmApp() {
    MaterialTheme {
        WwmContainer(
            circuit = wwmCircuit,
            initialScreen = AchievementsScreen,
        )
    }
}

/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.application

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.omico.wwm.feature.achievements.AchievementsScreen
import dev.omico.wwm.ui.WwmContainer

@Composable
fun WwmApp() {
    MaterialTheme {
        WwmContainer(
            component = remember(::WwmApplicationComponent),
            initialScreen = AchievementsScreen,
        )
    }
}

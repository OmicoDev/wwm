/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.slack.circuit.retained.collectAsRetainedState
import dev.omico.wwm.data.WwmAchievements
import dev.omico.wwm.ui.WwmUiComponent
import kotlinx.coroutines.launch

context(WwmUiComponent)
@Composable
internal fun produceAchievementsUiState(): AchievementsUiState {
    val scope = rememberCoroutineScope()
    val achievements by achievementsRepository.achievements.collectAsRetainedState(initial = WwmAchievements.Empty)
    var locale by remember { mutableStateOf(achievements.locale) }
    LaunchedEffect(Unit) { achievementsRepository.load() }
    LaunchedEffect(locale) { achievementsRepository.reloadMultiText(locale) }
    return AchievementsUiState(
        achievements = achievements,
        eventSink = { event ->
            when (event) {
                is AchievementsUiEvent.ChangeLocale -> locale = event.locale
                is AchievementsUiEvent.ChangeAchievementMark ->
                    scope.launch {
                        when {
                            event.marked -> achievementsRepository.markAchievement(event.achievementId)
                            else -> achievementsRepository.unmarkAchievement(event.achievementId)
                        }
                    }
            }
        },
    )
}

/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements

import com.slack.circuit.runtime.CircuitUiState
import dev.omico.wwm.data.WwmAchievements
import dev.omico.wwm.resources.model.game.WwAchievement

data class AchievementsUiState(
    val achievements: WwmAchievements,
    val eventSink: (AchievementsUiEvent) -> Unit,
) : CircuitUiState

internal fun AchievementsUiState.isAchievementMarked(achievement: WwAchievement): Boolean =
    achievement.id in achievements.markedAchievementIds

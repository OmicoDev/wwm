/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements

import com.slack.circuit.runtime.CircuitUiState
import dev.omico.wwm.data.WwmMarkedAchievementIds
import dev.omico.wwm.resources.model.game.WwAchievementCategories
import dev.omico.wwm.resources.model.game.WwAchievementGroups
import dev.omico.wwm.resources.model.game.WwAchievements
import dev.omico.wwm.resources.model.game.WwLocale
import dev.omico.wwm.resources.model.game.WwMultiText

data class AchievementsUiState(
    val achievements: WwAchievements,
    val achievementCategories: WwAchievementCategories,
    val achievementGroups: WwAchievementGroups,
    val multiText: WwMultiText,
    val locale: WwLocale,
    val markedAchievementIds: WwmMarkedAchievementIds,
    val eventSink: (AchievementsUiEvent) -> Unit,
) : CircuitUiState

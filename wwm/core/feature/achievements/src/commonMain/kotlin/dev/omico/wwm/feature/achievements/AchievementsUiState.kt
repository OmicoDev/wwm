/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.slack.circuit.runtime.CircuitUiState
import dev.omico.wwm.resources.model.game.WwAchievement
import dev.omico.wwm.resources.model.game.WwAchievementCategory
import dev.omico.wwm.resources.model.game.WwAchievementGroup
import dev.omico.wwm.resources.model.game.WwLocale
import dev.omico.wwm.resources.model.game.WwText

data class AchievementsUiState(
    val achievements: SnapshotStateList<WwAchievement>,
    val achievementCategories: SnapshotStateList<WwAchievementCategory>,
    val achievementGroups: SnapshotStateList<WwAchievementGroup>,
    val multiText: SnapshotStateList<WwText>,
    val locale: WwLocale,
    val markedAchievementIds: SnapshotStateList<Int>,
    val eventSink: (AchievementsUiEvent) -> Unit,
) : CircuitUiState

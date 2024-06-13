/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements

import com.slack.circuit.runtime.CircuitUiEvent
import dev.omico.wwm.resources.model.game.WwLocale

sealed interface AchievementsUiEvent : CircuitUiEvent {
    data class ChangeLocale(val locale: WwLocale) : AchievementsUiEvent

    data class ChangeAchievementMark(
        val marked: Boolean,
        val achievementId: Int,
    ) : AchievementsUiEvent

    data class ExportData(
        val onExported: (json: String) -> Unit,
    ) : AchievementsUiEvent

    data class ImportData(
        val json: String,
    ) : AchievementsUiEvent
}

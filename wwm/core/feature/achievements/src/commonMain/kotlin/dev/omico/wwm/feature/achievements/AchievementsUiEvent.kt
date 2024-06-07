/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements

import com.slack.circuit.runtime.CircuitUiEvent
import dev.omico.wwm.resources.model.game.WwLocale

sealed interface AchievementsUiEvent : CircuitUiEvent {
    data class ChangeLocale(val locale: WwLocale) : AchievementsUiEvent
}

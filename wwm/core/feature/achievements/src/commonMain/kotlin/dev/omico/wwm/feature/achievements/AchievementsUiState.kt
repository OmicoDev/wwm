/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements

import com.slack.circuit.runtime.CircuitUiState

data class AchievementsUiState(
    val title: String = "",
) : CircuitUiState

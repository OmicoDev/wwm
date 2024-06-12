/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import dev.omico.wwm.feature.achievements.AchievementsUiState
import dev.omico.wwm.resources.model.game.WwAchievementGroup

@Composable
internal fun AchievementsDetailPaneUi(
    state: AchievementsUiState,
    achievementGroup: WwAchievementGroup,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AchievementsDetailTopAppBar(
                state = state,
                achievementGroup = achievementGroup,
                onNavigateBack = onNavigateBack,
            )
        },
        content = { innerPadding ->
            AchievementsDetailContent(
                state = state,
                achievementGroup = achievementGroup,
                contentPadding = innerPadding,
            )
        },
    )
}

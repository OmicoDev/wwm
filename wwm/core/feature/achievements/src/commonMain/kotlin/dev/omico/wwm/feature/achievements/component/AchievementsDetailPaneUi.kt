/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import dev.omico.wwm.feature.achievements.AchievementsUiState

@Composable
internal fun AchievementsDetailPaneUi(
    state: AchievementsUiState,
    achievementGroupId: Int,
    achievementGroupName: String,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AchievementsDetailTopAppBar(
                state = state,
                achievementGroupId = achievementGroupId,
                achievementGroupName = achievementGroupName,
                onNavigateBack = onNavigateBack,
            )
        },
        content = { innerPadding ->
            AchievementsDetailContent(
                state = state,
                achievementGroupId = achievementGroupId,
                contentPadding = innerPadding,
            )
        },
    )
}

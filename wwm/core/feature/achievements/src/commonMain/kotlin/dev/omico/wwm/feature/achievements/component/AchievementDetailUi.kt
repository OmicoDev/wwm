/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import dev.omico.wwm.feature.achievements.AchievementsUiState

@Composable
internal fun AchievementDetailPaneUi(
    state: AchievementsUiState,
    achievementGroupId: Int,
    achievementGroupName: String,
    showNavigationIcon: Boolean,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AchievementDetailTopAppBar(
                state = state,
                achievementGroupId = achievementGroupId,
                achievementGroupName = achievementGroupName,
                showNavigationIcon = showNavigationIcon,
                onNavigateBack = onNavigateBack,
            )
        },
        content = { innerPadding ->
            AchievementDetailContent(
                state = state,
                achievementGroupId = achievementGroupId,
                contentPadding = innerPadding,
            )
        },
    )
}

/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.omico.wwm.feature.achievements.AchievementsUiState
import dev.omico.wwm.resources.model.game.WwAchievementGroup

@Composable
internal fun AchievementsDetailPaneUi(
    state: AchievementsUiState,
    achievementGroup: WwAchievementGroup,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.widthIn(
            min = 360.dp,
            max = 800.dp,
        ),
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

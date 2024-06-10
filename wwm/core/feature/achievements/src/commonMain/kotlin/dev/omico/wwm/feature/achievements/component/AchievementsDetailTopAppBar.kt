/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.navigationsuite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import dev.omico.wwm.feature.achievements.AchievementsUiState
import dev.omico.wwm.resources.model.game.WwAchievementGroup
import dev.omico.wwm.resources.rememberWwText
import dev.omico.wwm.ui.LocalNavigationSuiteType

@OptIn(
    ExperimentalMaterial3AdaptiveNavigationSuiteApi::class,
    ExperimentalMaterial3Api::class,
)
@Composable
internal fun AchievementsDetailTopAppBar(
    state: AchievementsUiState,
    achievementGroup: WwAchievementGroup,
    onNavigateBack: () -> Unit,
) {
    TopAppBar(
        title = {
            val achievementGroupName = rememberWwText(
                multiText = state.multiText,
                name = achievementGroup.name,
            )
            val markedAchievementIds by rememberUpdatedState(state.markedAchievementIds)
            val achievementGroupId by rememberUpdatedState(achievementGroup.id)
            val countAchievementGroup by remember(achievementGroupId) {
                derivedStateOf {
                    state.achievements.count { achievement -> achievement.groupId == achievementGroupId }
                }
            }
            val countMarkedAchievementGroup by remember(achievementGroupId, markedAchievementIds) {
                derivedStateOf {
                    state.achievements.count { achievement ->
                        achievement.groupId == achievementGroupId && achievement.id in markedAchievementIds
                    }
                }
            }
            Text(text = "$achievementGroupName $countMarkedAchievementGroup/$countAchievementGroup")
        },
        navigationIcon = {
            if (LocalNavigationSuiteType.current == NavigationSuiteType.NavigationBar) {
                IconButton(
                    onClick = onNavigateBack,
                    content = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back",
                        )
                    },
                )
            }
        },
    )
}

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
import dev.omico.wwm.feature.achievements.AchievementsUiState
import dev.omico.wwm.ui.LocalNavigationSuiteType

@OptIn(
    ExperimentalMaterial3AdaptiveNavigationSuiteApi::class,
    ExperimentalMaterial3Api::class,
)
@Composable
internal fun AchievementsDetailTopAppBar(
    state: AchievementsUiState,
    achievementGroupId: Int,
    achievementGroupName: String,
    onNavigateBack: () -> Unit,
) {
    TopAppBar(
        title = {
            val achievementCount by remember(achievementGroupId) {
                derivedStateOf {
                    state.achievements.count { achievement ->
                        achievement.groupId == achievementGroupId &&
                            achievement.hidden.not()
                    }
                }
            }
            val markedAchievementCount by remember(achievementGroupId, state.markedAchievementIds) {
                derivedStateOf {
                    state.achievements.count { achievement ->
                        achievement.groupId == achievementGroupId &&
                            achievement.id in state.markedAchievementIds &&
                            achievement.hidden.not()
                    }
                }
            }
            val achievementPercentage by remember(achievementCount, markedAchievementCount) {
                derivedStateOf { markedAchievementCount * 100 / achievementCount }
            }
            val title by remember(
                achievementGroupName,
                achievementPercentage,
                markedAchievementCount,
                achievementCount,
            ) {
                derivedStateOf {
                    "$achievementGroupName $achievementPercentage% ($markedAchievementCount/$achievementCount)"
                }
            }
            Text(text = title)
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

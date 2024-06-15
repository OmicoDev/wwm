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
            val groupState = AchievementGroupState(
                state = state,
                groupId = achievementGroupId,
            )
            val title by remember(achievementGroupName, groupState) {
                derivedStateOf {
                    buildString {
                        append(achievementGroupName)
                        append("  ")
                        append(groupState.achievementPercentage)
                        append("  ")
                        append("(${groupState.markedAchievementCount}/${groupState.achievementCount})")
                    }
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

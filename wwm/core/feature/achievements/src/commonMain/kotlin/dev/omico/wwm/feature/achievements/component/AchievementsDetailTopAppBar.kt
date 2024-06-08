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
            Text(
                text = rememberWwText(
                    multiText = state.achievements.multiText,
                    name = achievementGroup.name,
                ),
            )
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

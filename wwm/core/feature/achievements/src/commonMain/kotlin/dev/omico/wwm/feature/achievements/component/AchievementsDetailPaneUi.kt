/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.navigationsuite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.omico.wwm.feature.achievements.AchievementsUiState
import dev.omico.wwm.resources.model.game.WwAchievement
import dev.omico.wwm.resources.model.game.WwAchievementGroup
import dev.omico.wwm.resources.rememberWwText
import dev.omico.wwm.ui.LocalNavigationSuiteType

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3AdaptiveNavigationSuiteApi::class,
    ExperimentalMaterial3Api::class,
)
@Composable
internal fun AchievementsDetailPaneUi(
    state: AchievementsUiState,
    achievementGroup: WwAchievementGroup,
    onNavigateBack: () -> Unit,
    onAchievementClick: (WwAchievement) -> Unit,
) {
    Scaffold(
        modifier = Modifier.widthIn(
            min = 360.dp,
            max = 800.dp,
        ),
        topBar = {
            val text = rememberWwText(
                multiText = state.achievements.multiText,
                name = achievementGroup.name,
            )
            TopAppBar(
                title = { Text(text) },
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
        },
        content = { innerPadding ->
            val achievements by remember(achievementGroup.id) {
                derivedStateOf {
                    state.achievements.achievements.filter { achievement -> achievement.groupId == achievementGroup.id }
                }
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(space = 8.dp),
                contentPadding = innerPadding,
                content = {
                    items(
                        items = achievements,
                        key = WwAchievement::id,
                        itemContent = { achievement ->
                            AchievementsDetailItem(
                                text = rememberWwText(
                                    multiText = state.achievements.multiText,
                                    name = achievement.name,
                                ),
                                modifier = Modifier.animateItemPlacement(),
                                onClick = { onAchievementClick(achievement) },
                            )
                        },
                    )
                },
            )
        },
    )
}

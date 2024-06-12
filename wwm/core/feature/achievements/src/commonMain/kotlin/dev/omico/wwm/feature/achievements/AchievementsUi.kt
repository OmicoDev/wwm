/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.omico.wwm.feature.achievements.component.AchievementsDetailPaneUi
import dev.omico.wwm.feature.achievements.component.AchievementsListPaneUi
import dev.omico.wwm.resources.model.game.WwAchievementGroup

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal fun AchievementsUi(
    state: AchievementsUiState,
    modifier: Modifier = Modifier,
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<WwAchievementGroup>()
    Box(
        modifier = modifier.fillMaxSize(),
        content = {
            ListDetailPaneScaffold(
                listPane = {
                    AchievementsListPaneUi(
                        state = state,
                        onGroupItemClick = { achievementGroup ->
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                content = achievementGroup,
                            )
                        },
                    )
                },
                directive = navigator.scaffoldDirective,
                value = navigator.scaffoldValue,
                detailPane = {
                    AnimatedPane(
                        modifier = Modifier,
                        content = {
                            AchievementsDetailPaneUi(
                                state = state,
                                achievementGroup = navigator.currentDestination?.content ?: return@AnimatedPane,
                                onNavigateBack = navigator::navigateBack,
                            )
                        },
                    )
                },
                modifier = run {
                    Modifier
                        .align(alignment = Alignment.TopCenter)
                        .widthIn(
                            min = 360.dp,
                            max = 1200.dp,
                        )
                },
            )
        },
    )
}

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
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.omico.wwm.feature.achievements.component.AchievementDetailPaneUi
import dev.omico.wwm.feature.achievements.component.AchievementListPaneUi
import dev.omico.wwm.resources.model.game.WwAchievementGroup
import dev.omico.wwm.ui.rememberWwText

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
                    AchievementListPaneUi(
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
                    val showNavigationIcon by produceState(
                        initialValue = false,
                        navigator.scaffoldValue.secondary,
                        producer = { value = navigator.scaffoldValue.secondary == PaneAdaptedValue.Hidden },
                    )
                    AnimatedPane(
                        modifier = Modifier,
                        content = {
                            val achievementGroup = navigator.currentDestination?.content ?: return@AnimatedPane
                            AchievementDetailPaneUi(
                                state = state,
                                achievementGroupId = achievementGroup.id,
                                achievementGroupName = rememberWwText(
                                    multiText = state.multiText,
                                    name = achievementGroup.name,
                                ),
                                showNavigationIcon = showNavigationIcon,
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

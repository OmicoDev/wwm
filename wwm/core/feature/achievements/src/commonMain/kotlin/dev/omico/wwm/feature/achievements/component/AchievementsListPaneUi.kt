/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.omico.wwm.feature.achievements.AchievementsUiState
import dev.omico.wwm.resources.WwmIcons
import dev.omico.wwm.resources.model.game.WwAchievementCategory
import dev.omico.wwm.resources.model.game.WwAchievementGroup
import dev.omico.wwm.resources.rememberWwText
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AchievementsListPaneUi(
    state: AchievementsUiState,
    onGroupItemClick: (WwAchievementGroup) -> Unit,
) {
    val categoriesToggleState = remember(::AchievementCategoriesToggleState)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    val markedAchievementIds by rememberUpdatedState(state.markedAchievementIds)
                    val countAchievement by remember { derivedStateOf { state.achievements.count() } }
                    val countMarkedAchievement by remember(markedAchievementIds) {
                        derivedStateOf {
                            state.achievements.count { achievement -> achievement.id in markedAchievementIds }
                        }
                    }
                    Text("Achievements $countMarkedAchievement/$countAchievement")
                },
                actions = {
                    IconButton(
                        onClick = categoriesToggleState::collapseAll,
                        content = {
                            Icon(
                                painter = painterResource(resource = WwmIcons.CollapseAll),
                                contentDescription = "Collapse all",
                            )
                        },
                    )
                    IconButton(
                        onClick = categoriesToggleState::expandAll,
                        content = {
                            Icon(
                                painter = painterResource(resource = WwmIcons.ExpandAll),
                                contentDescription = "Expand all",
                            )
                        },
                    )
                },
            )
        },
        content = { innerPadding ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(space = 8.dp),
                contentPadding = innerPadding,
                content = {
                    state.achievementCategories.forEach { category ->
                        achievementsCategoryItem(
                            state = state,
                            category = category,
                            isExpanded = categoriesToggleState.isExpanded(category.id),
                            onCategoryItemClick = { categoriesToggleState.toggle(category.id) },
                            onGroupItemClick = onGroupItemClick,
                        )
                    }
                },
            )
        },
    )
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.achievementsCategoryItem(
    state: AchievementsUiState,
    category: WwAchievementCategory,
    isExpanded: Boolean,
    onCategoryItemClick: () -> Unit,
    onGroupItemClick: (WwAchievementGroup) -> Unit,
) {
    item(
        key = category.id,
        content = {
            AchievementsListItem(
                text = rememberWwText(
                    multiText = state.multiText,
                    name = category.name,
                ),
                modifier = Modifier.animateItemPlacement(),
                onClick = onCategoryItemClick,
            )
        },
    )
    if (isExpanded) {
        items(
            items = state.achievementGroups.filter { it.category == category.id },
            key = WwAchievementGroup::id,
            itemContent = { achievementGroup ->
                AchievementsListItem(
                    text = rememberWwText(
                        multiText = state.multiText,
                        name = achievementGroup.name,
                    ),
                    modifier = run {
                        Modifier
                            .padding(start = 16.dp)
                            .animateItemPlacement()
                    },
                    onClick = { onGroupItemClick(achievementGroup) },
                )
            },
        )
    }
}

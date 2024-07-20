/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.omico.wwm.feature.achievements.AchievementsUiState
import dev.omico.wwm.resources.model.game.WwAchievementCategory
import dev.omico.wwm.resources.model.game.WwAchievementGroup
import dev.omico.wwm.ui.rememberWwText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AchievementListPaneUi(
    state: AchievementsUiState,
    onGroupItemClick: (WwAchievementGroup) -> Unit,
) {
    val categoriesToggleState = remember(::AchievementCategoriesToggleState)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    val countAchievement by remember { derivedStateOf { state.achievements.count() } }
                    val countMarkedAchievement by remember(state.markedAchievementIds) {
                        derivedStateOf {
                            state.achievements.count { achievement -> achievement.id in state.markedAchievementIds }
                        }
                    }
                    Text("Achievements $countMarkedAchievement/$countAchievement")
                },
                actions = {
                    AchievementsListPaneTopAppBarActions(
                        state = state,
                        categoriesToggleState = categoriesToggleState,
                    )
                },
            )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = run {
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                },
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
        contentType = category::class,
        content = {
            AchievementListItem(
                text = rememberWwText(
                    multiText = state.multiText,
                    name = category.name,
                ),
                modifier = Modifier.animateItem(),
                onClick = onCategoryItemClick,
            )
        },
    )
    items(
        items = when {
            isExpanded ->
                state.achievementGroups
                    .filter { achievementGroup -> achievementGroup.category == category.id }
            else -> emptyList()
        },
        key = WwAchievementGroup::id,
        contentType = { achievementGroup -> achievementGroup::class },
        itemContent = { achievementGroup ->
            val achievementGroupName = rememberWwText(
                multiText = state.multiText,
                name = achievementGroup.name,
            )
            val groupState = AchievementGroupState(
                state = state,
                groupId = achievementGroup.id,
            )
            val text by remember(achievementGroupName, groupState) {
                derivedStateOf { "$achievementGroupName  ${groupState.achievementPercentage}" }
            }
            AchievementListItem(
                text = text,
                modifier = run {
                    Modifier
                        .padding(start = 16.dp)
                        .animateItem()
                },
                onClick = { onGroupItemClick(achievementGroup) },
            )
        },
    )
}

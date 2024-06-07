/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.omico.wwm.resources.model.game.WwAchievementCategory

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal fun AchievementsUi(
    state: AchievementsUiState,
    modifier: Modifier = Modifier,
) {
    ListDetailPaneScaffold(
        listPane = {
            val categoryToggleStates: SnapshotStateMap<Int, Boolean> = remember { mutableStateMapOf() }
            LazyColumn(verticalArrangement = Arrangement.spacedBy(space = 8.dp)) {
                state.achievements.achievementCategories.forEach { category ->
                    achievementsCategoryItem(
                        state = state,
                        category = category,
                        isExpanded = categoryToggleStates.getOrPut(category.id) { false },
                        onClick = {
                            categoryToggleStates[category.id] =
                                categoryToggleStates.getOrPut(category.id) { false }.not()
                        },
                    )
                }
            }
        },
        detailPane = { Box(modifier = Modifier.background(color = Color.DarkGray)) },
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.achievementsCategoryItem(
    state: AchievementsUiState,
    category: WwAchievementCategory,
    isExpanded: Boolean,
    onClick: () -> Unit,
) {
    item {
        val text by remember {
            derivedStateOf {
                state.achievements.multiText
                    .firstOrNull { it.id == category.name }
                    ?.content ?: category.name
            }
        }
        AchievementsListItem(
            text = text,
            modifier = Modifier.animateItemPlacement(),
            onClick = onClick,
        )
    }
    if (isExpanded) {
        items(state.achievements.achievementGroups.filter { it.category == category.id }) { achievementGroup ->
            val text by remember {
                derivedStateOf {
                    state.achievements.multiText
                        .firstOrNull { it.id == achievementGroup.name }
                        ?.content ?: achievementGroup.name
                }
            }
            AchievementsListItem(
                text = text,
                modifier = run {
                    Modifier
                        .padding(start = 16.dp)
                        .animateItemPlacement()
                },
                onClick = {},
            )
        }
    }
}

@Composable
fun AchievementsListItem(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        content = {
            Box(modifier = Modifier.padding(all = 16.dp)) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        },
    )
}

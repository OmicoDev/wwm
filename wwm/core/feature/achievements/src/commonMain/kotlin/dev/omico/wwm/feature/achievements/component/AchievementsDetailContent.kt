/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import dev.omico.wwm.feature.achievements.AchievementsUiEvent
import dev.omico.wwm.feature.achievements.AchievementsUiState
import dev.omico.wwm.feature.achievements.isAchievementMarked
import dev.omico.wwm.resources.model.game.WwAchievement
import dev.omico.wwm.resources.model.game.WwAchievementGroup
import dev.omico.wwm.resources.rememberWwText

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun AchievementsDetailContent(
    state: AchievementsUiState,
    achievementGroup: WwAchievementGroup,
    contentPadding: PaddingValues,
) {
    val achievements by remember(achievementGroup.id) {
        derivedStateOf {
            state.achievements.achievements.filter { achievement -> achievement.groupId == achievementGroup.id }
        }
    }
    LazyColumn(
        contentPadding = contentPadding,
        content = {
            items(
                items = achievements.sortedBy(state::isAchievementMarked),
                key = WwAchievement::id,
                itemContent = { achievement ->
                    AchievementsDetailItem(
                        marked = state.isAchievementMarked(achievement),
                        onMarkedChange = { marked ->
                            state.eventSink(
                                AchievementsUiEvent.ChangeAchievementMark(
                                    marked = marked,
                                    achievementId = achievement.id,
                                ),
                            )
                        },
                        name = rememberWwText(
                            multiText = state.achievements.multiText,
                            name = achievement.name,
                        ),
                        description = rememberWwText(
                            multiText = state.achievements.multiText,
                            name = achievement.desc,
                        ),
                        modifier = Modifier.animateItemPlacement(),
                    )
                },
            )
        },
    )
}

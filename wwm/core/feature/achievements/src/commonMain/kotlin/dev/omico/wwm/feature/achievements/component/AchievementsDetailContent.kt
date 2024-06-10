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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import dev.omico.wwm.feature.achievements.AchievementsUiEvent
import dev.omico.wwm.feature.achievements.AchievementsUiState
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
    val achievementGroupId by rememberUpdatedState(achievementGroup.id)
    val markedAchievementIds by rememberUpdatedState(state.markedAchievementIds)
    val achievements by remember(achievementGroupId, markedAchievementIds) {
        derivedStateOf {
            state.achievements
                .filter { achievement -> achievement.groupId == achievementGroupId }
                .sortedBy { achievement -> achievement.id in markedAchievementIds }
        }
    }
    LazyColumn(
        contentPadding = contentPadding,
        content = {
            items(
                items = achievements,
                key = WwAchievement::id,
                itemContent = { achievement ->
                    AchievementsDetailItem(
                        marked = achievement.id in markedAchievementIds,
                        onMarkedChange = { marked ->
                            state.eventSink(
                                AchievementsUiEvent.ChangeAchievementMark(
                                    marked = marked,
                                    achievementId = achievement.id,
                                ),
                            )
                        },
                        name = rememberWwText(
                            multiText = state.multiText,
                            name = achievement.name,
                        ),
                        description = rememberWwText(
                            multiText = state.multiText,
                            name = achievement.desc,
                        ),
                        modifier = Modifier.animateItemPlacement(),
                    )
                },
            )
        },
    )
}

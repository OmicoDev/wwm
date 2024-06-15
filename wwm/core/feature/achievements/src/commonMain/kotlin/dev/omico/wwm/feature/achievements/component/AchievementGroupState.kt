/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import dev.omico.wwm.feature.achievements.AchievementsUiState

@Immutable
data class AchievementGroupState(
    val groupId: Int,
    val achievementCount: Int,
    val markedAchievementCount: Int,
) {
    @Suppress("MagicNumber")
    val achievementPercentage: String = "${markedAchievementCount * 100 / achievementCount}%"
}

@Composable
fun AchievementGroupState(state: AchievementsUiState, groupId: Int): AchievementGroupState {
    val achievementCount: Int by remember(groupId, state.achievements) {
        derivedStateOf {
            state.achievements.count { achievement ->
                achievement.groupId == groupId &&
                    achievement.hidden.not()
            }
        }
    }
    val markedAchievementCount: Int by remember(groupId, state.achievements, state.markedAchievementIds) {
        derivedStateOf {
            state.achievements.count { achievement ->
                achievement.groupId == groupId &&
                    achievement.id in state.markedAchievementIds &&
                    achievement.hidden.not()
            }
        }
    }
    val groupState: AchievementGroupState by remember(groupId, achievementCount, markedAchievementCount) {
        derivedStateOf {
            AchievementGroupState(
                groupId = groupId,
                achievementCount = achievementCount,
                markedAchievementCount = markedAchievementCount,
            )
        }
    }
    return groupState
}

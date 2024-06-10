/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.data

import dev.omico.wwm.resources.model.game.WwAchievementCategories
import dev.omico.wwm.resources.model.game.WwAchievementGroups
import dev.omico.wwm.resources.model.game.WwAchievements
import dev.omico.wwm.resources.model.game.WwLocale
import dev.omico.wwm.resources.model.game.WwMultiText
import kotlinx.coroutines.flow.Flow

interface AchievementsRepository {
    val achievements: Flow<WwAchievements>
    val achievementCategories: Flow<WwAchievementCategories>
    val achievementGroups: Flow<WwAchievementGroups>
    val multiText: Flow<WwMultiText>
    val locale: Flow<WwLocale>
    val markedAchievementIds: Flow<WwmMarkedAchievementIds>
    suspend fun load()
    suspend fun reloadAchievements()
    suspend fun reloadAchievementCategories()
    suspend fun reloadAchievementGroups()
    suspend fun reloadMultiText(locale: WwLocale)
    suspend fun markAchievement(marked: Boolean, achievementId: Int)
}

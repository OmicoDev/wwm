/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.data

import dev.omico.wwm.resources.model.game.WwLocale
import kotlinx.coroutines.flow.Flow

interface AchievementsRepository {
    val achievements: Flow<WwmAchievements>
    suspend fun load()
    suspend fun reloadAchievements()
    suspend fun reloadAchievementCategories()
    suspend fun reloadAchievementGroups()
    suspend fun reloadMultiText(locale: WwLocale)
}

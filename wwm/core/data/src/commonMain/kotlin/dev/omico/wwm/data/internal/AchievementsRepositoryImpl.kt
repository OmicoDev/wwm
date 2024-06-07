/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.data.internal

import dev.omico.wwm.data.AchievementsRepository
import dev.omico.wwm.data.WwmAchievements
import dev.omico.wwm.resources.WwmResources
import dev.omico.wwm.resources.model.game.WwAchievementCategories
import dev.omico.wwm.resources.model.game.WwAchievementGroups
import dev.omico.wwm.resources.model.game.WwAchievements
import dev.omico.wwm.resources.model.game.WwLocale
import dev.omico.wwm.resources.model.game.WwMultiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

internal class AchievementsRepositoryImpl : AchievementsRepository {
    private val wwAchievements: MutableStateFlow<WwAchievements> = MutableStateFlow(emptyList())
    private val wwAchievementCategories: MutableStateFlow<WwAchievementCategories> = MutableStateFlow(emptyList())
    private val wwAchievementGroups: MutableStateFlow<WwAchievementGroups> = MutableStateFlow(emptyList())
    private val wwMultiText: MutableStateFlow<WwMultiText> = MutableStateFlow(emptyList())

    override val achievements: Flow<WwmAchievements> =
        combine(
            wwAchievements,
            wwAchievementCategories,
            wwAchievementGroups,
            wwMultiText,
            ::WwmAchievements,
        )

    override suspend fun load() {
        reloadAchievements()
        reloadAchievementCategories()
        reloadAchievementGroups()
    }

    override suspend fun reloadAchievements(): Unit = wwAchievements.emit(WwmResources.loadAchievements())

    override suspend fun reloadAchievementCategories(): Unit =
        wwAchievementCategories.emit(WwmResources.loadAchievementCategories())

    override suspend fun reloadAchievementGroups(): Unit =
        wwAchievementGroups.emit(WwmResources.loadAchievementGroups())

    override suspend fun reloadMultiText(locale: WwLocale): Unit = wwMultiText.emit(WwmResources.loadMultiText(locale))
}

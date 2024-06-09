/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.data.internal

import dev.omico.wwm.data.AchievementsRepository
import dev.omico.wwm.data.WwmAchievements
import dev.omico.wwm.data.WwmMarkedAchievementIds
import dev.omico.wwm.resources.WwmResources
import dev.omico.wwm.resources.model.game.WwAchievementCategories
import dev.omico.wwm.resources.model.game.WwAchievementGroups
import dev.omico.wwm.resources.model.game.WwAchievements
import dev.omico.wwm.resources.model.game.WwLocale
import dev.omico.wwm.resources.model.game.WwMultiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class AchievementsRepositoryImpl(
    private val markedAchievementsDataStore: MarkedAchievementsDataStore,
) : AchievementsRepository {
    private val wwAchievements: MutableStateFlow<WwAchievements> = MutableStateFlow(emptyList())
    private val wwAchievementCategories: MutableStateFlow<WwAchievementCategories> = MutableStateFlow(emptyList())
    private val wwAchievementGroups: MutableStateFlow<WwAchievementGroups> = MutableStateFlow(emptyList())
    private val wwMultiText: MutableStateFlow<WwMultiText> = MutableStateFlow(emptyList())
    private val wwLocale: MutableStateFlow<WwLocale> = MutableStateFlow(WwLocale.ZH_HANS)
    private val markedAchievementIds: MutableStateFlow<WwmMarkedAchievementIds> = MutableStateFlow(emptySet())

    @Suppress("UNCHECKED_CAST")
    override val achievements: Flow<WwmAchievements> =
        combine(
            wwAchievements,
            wwAchievementCategories,
            wwAchievementGroups,
            wwMultiText,
            wwLocale,
            markedAchievementIds,
        ) { arguments ->
            WwmAchievements(
                achievements = arguments[0] as WwAchievements,
                achievementCategories = arguments[1] as WwAchievementCategories,
                achievementGroups = arguments[2] as WwAchievementGroups,
                multiText = arguments[3] as WwMultiText,
                locale = arguments[4] as WwLocale,
                markedAchievementIds = arguments[5] as WwmMarkedAchievementIds,
            )
        }

    override suspend fun load() {
        reloadAchievements()
        reloadAchievementCategories()
        reloadAchievementGroups()
    }

    override suspend fun reloadAchievements() {
        wwAchievements.emit(WwmResources.loadAchievements())
        markedAchievementIds.emit(markedAchievementsDataStore.load())
    }

    override suspend fun reloadAchievementCategories(): Unit =
        wwAchievementCategories.emit(WwmResources.loadAchievementCategories())

    override suspend fun reloadAchievementGroups(): Unit =
        wwAchievementGroups.emit(WwmResources.loadAchievementGroups())

    override suspend fun reloadMultiText(locale: WwLocale) {
        wwMultiText.emit(WwmResources.loadMultiText(locale))
        wwLocale.emit(locale)
    }

    override suspend fun markAchievement(achievementId: Int): Unit =
        saveMarkedAchievementIds(markedAchievementIds.value + achievementId)

    override suspend fun unmarkAchievement(achievementId: Int): Unit =
        saveMarkedAchievementIds(markedAchievementIds.value - achievementId)

    private val markedAchievementIdsMutex: Mutex = Mutex()

    private suspend fun saveMarkedAchievementIds(ids: WwmMarkedAchievementIds): Unit =
        markedAchievementIdsMutex.withLock {
            markedAchievementsDataStore.save(ids)
            markedAchievementIds.emit(ids)
        }
}

/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.data.internal

import dev.omico.wwm.data.AchievementsRepository
import dev.omico.wwm.data.WwmMarkedAchievementIds
import dev.omico.wwm.resources.WwmResources
import dev.omico.wwm.resources.model.game.WwAchievementCategories
import dev.omico.wwm.resources.model.game.WwAchievementGroups
import dev.omico.wwm.resources.model.game.WwAchievements
import dev.omico.wwm.resources.model.game.WwLocale
import dev.omico.wwm.resources.model.game.WwMultiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class AchievementsRepositoryImpl(
    private val markedAchievementsDataStore: MarkedAchievementsDataStore,
) : AchievementsRepository {
    private val wwAchievements: MutableSharedFlow<WwAchievements> = MutableSharedFlow(replay = 1)
    private val wwAchievementCategories: MutableSharedFlow<WwAchievementCategories> = MutableSharedFlow(replay = 1)
    private val wwAchievementGroups: MutableSharedFlow<WwAchievementGroups> = MutableSharedFlow(replay = 1)
    private val wwMultiText: MutableSharedFlow<WwMultiText> = MutableSharedFlow(replay = 1)
    private val wwLocale: MutableSharedFlow<WwLocale> = MutableSharedFlow(replay = 1)
    private val _markedAchievementIds: MutableStateFlow<WwmMarkedAchievementIds> = MutableStateFlow(value = emptySet())

    override val achievements: Flow<WwAchievements> = wwAchievements.asSharedFlow()
    override val achievementCategories: Flow<WwAchievementCategories> = wwAchievementCategories.asSharedFlow()
    override val achievementGroups: Flow<WwAchievementGroups> = wwAchievementGroups.asSharedFlow()
    override val multiText: Flow<WwMultiText> = wwMultiText.asSharedFlow()
    override val locale: Flow<WwLocale> = wwLocale.asSharedFlow()
    override val markedAchievementIds: Flow<WwmMarkedAchievementIds> = _markedAchievementIds.asSharedFlow()

    override suspend fun load() {
        reloadAchievements()
        reloadAchievementCategories()
        reloadAchievementGroups()
    }

    override suspend fun reloadAchievements() {
        wwAchievements.emit(WwmResources.loadAchievements())
        _markedAchievementIds.emit(markedAchievementsDataStore.load())
    }

    override suspend fun reloadAchievementCategories(): Unit =
        wwAchievementCategories.emit(WwmResources.loadAchievementCategories())

    override suspend fun reloadAchievementGroups(): Unit =
        wwAchievementGroups.emit(WwmResources.loadAchievementGroups())

    override suspend fun reloadMultiText(locale: WwLocale) {
        wwMultiText.emit(WwmResources.loadMultiText(locale))
        wwLocale.emit(locale)
    }

    private val markedAchievementIdsMutex: Mutex = Mutex()

    override suspend fun markAchievement(marked: Boolean, achievementId: Int): Unit =
        markedAchievementIdsMutex.withLock {
            val ids = when {
                marked -> _markedAchievementIds.value + achievementId
                else -> _markedAchievementIds.value - achievementId
            }
            markedAchievementsDataStore.save(ids)
            _markedAchievementIds.emit(ids)
        }
}

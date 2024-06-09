/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.data.internal

import dev.omico.wwm.data.WwmMarkedAchievementIds
import kotlinx.browser.localStorage

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING") // TODO KT-61573
internal actual class MarkedAchievementsDataStore {
    actual suspend fun load(): WwmMarkedAchievementIds {
        val achievements = localStorage.getItem(ACHIEVEMENTS_KEY) ?: return emptySet()
        return achievements.split(",").map(String::toInt).toSet()
    }

    actual suspend fun save(ids: WwmMarkedAchievementIds): Unit =
        localStorage.setItem(ACHIEVEMENTS_KEY, ids.joinToString(","))
}

private const val ACHIEVEMENTS_KEY = "achievements"

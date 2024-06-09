/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.data.internal

import dev.omico.wwm.data.WwmMarkedAchievementIds

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING") // TODO KT-61573
internal expect class MarkedAchievementsDataStore() {
    suspend fun load(): WwmMarkedAchievementIds
    suspend fun save(ids: WwmMarkedAchievementIds)
}

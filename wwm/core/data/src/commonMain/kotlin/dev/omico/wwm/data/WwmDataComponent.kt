/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.data

import dev.omico.wwm.data.internal.AchievementsRepositoryImpl
import dev.omico.wwm.data.internal.MarkedAchievementsDataStore

interface WwmDataComponent {
    val achievementsRepository: AchievementsRepository
    fun provideAchievementsRepository(): AchievementsRepository =
        AchievementsRepositoryImpl(
            markedAchievementsDataStore = MarkedAchievementsDataStore(),
        )
}

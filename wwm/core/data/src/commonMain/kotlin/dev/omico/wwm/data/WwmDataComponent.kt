/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.data

import dev.omico.wwm.data.internal.AchievementsRepositoryImpl

interface WwmDataComponent {
    val achievementsRepository: AchievementsRepository
    fun provideAchievementsRepository(): AchievementsRepository = AchievementsRepositoryImpl()
}

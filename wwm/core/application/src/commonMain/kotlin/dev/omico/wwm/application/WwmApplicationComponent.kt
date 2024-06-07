/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.application

import com.slack.circuit.foundation.Circuit
import dev.omico.wwm.data.AchievementsRepository
import dev.omico.wwm.feature.achievements.addAchievementsFeature
import dev.omico.wwm.ui.WwmUiComponent

class WwmApplicationComponent : WwmUiComponent {
    override val circuit: Circuit by lazy(::provideCircuit)

    override fun provideCircuit(): Circuit =
        Circuit.Builder()
            .addAchievementsFeature()
            .build()

    override val achievementsRepository: AchievementsRepository by lazy(::provideAchievementsRepository)
}

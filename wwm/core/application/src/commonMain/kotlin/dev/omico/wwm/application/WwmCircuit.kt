/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.application

import com.slack.circuit.foundation.Circuit
import dev.omico.wwm.feature.achievements.addAchievementsFeature

val wwmCircuit: Circuit = Circuit.Builder()
    .addAchievementsFeature()
    .build()

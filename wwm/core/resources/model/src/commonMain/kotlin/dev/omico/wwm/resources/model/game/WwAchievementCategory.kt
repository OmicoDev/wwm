/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.resources.model.game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WwAchievementCategory(
    @SerialName("Id") val id: Int,
    @SerialName("Name") val name: String,
    @SerialName("SpritePath") val spritePath: String,
    @SerialName("TexturePath") val texturePath: String,
)

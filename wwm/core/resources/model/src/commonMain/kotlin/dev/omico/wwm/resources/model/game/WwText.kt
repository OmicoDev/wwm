/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.resources.model.game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WwText(
    @SerialName("Id") val id: String,
    @SerialName("Content") val content: String,
)

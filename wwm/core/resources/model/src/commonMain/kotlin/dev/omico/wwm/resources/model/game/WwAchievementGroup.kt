/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.resources.model.game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WwAchievementGroup(
    @SerialName("Id") val id: Int,
    @SerialName("Category") val category: Int,
    @SerialName("Sort") val sort: Int,
    @SerialName("Name") val name: String,
    @SerialName("SmallIcon") val smallIcon: String,
    @SerialName("Icon") val icon: String,
    @SerialName("BackgroundIcon") val backgroundIcon: String,
    @SerialName("DropId") val dropId: Int,
    @SerialName("Enable") val enable: Boolean,
)

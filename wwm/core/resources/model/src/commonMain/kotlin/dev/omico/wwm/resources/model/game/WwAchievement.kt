/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.resources.model.game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WwAchievement(
    @SerialName("Id") val id: Int,
    @SerialName("GroupId") val groupId: Int,
    @SerialName("Level") val level: Int,
    @SerialName("Name") val name: String,
    @SerialName("Desc") val desc: String,
    @SerialName("IconPath") val iconPath: String = "",
    @SerialName("OverrideDropId") val overrideDropId: Int = 0,
    @SerialName("Hidden") val hidden: Boolean = false,
    @SerialName("NextLink") val nextLink: Int = -1,
    @SerialName("ClientTrigger") val clientTrigger: Boolean = false,
)

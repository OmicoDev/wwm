/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.resources

import dev.omico.wwm.resources.generated.Res
import dev.omico.wwm.resources.generated.WutheringWaves
import dev.omico.wwm.resources.model.game.WwAchievementCategories
import dev.omico.wwm.resources.model.game.WwAchievementGroups
import dev.omico.wwm.resources.model.game.WwAchievements
import dev.omico.wwm.resources.model.game.WwLocale
import dev.omico.wwm.resources.model.game.WwMultiText
import dev.omico.wwm.resources.runtime.WwmResourcesPaths
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

object WwmResources {
    val WutheringWavesIcon: DrawableResource = Res.drawable.WutheringWaves

    suspend fun loadAchievements(): WwAchievements = readJsonResource(WwmResourcesPaths.AchievementPath)

    suspend fun loadAchievementCategories(): WwAchievementCategories =
        readJsonResource(WwmResourcesPaths.AchievementCategoryPath)

    suspend fun loadAchievementGroups(): WwAchievementGroups = readJsonResource(WwmResourcesPaths.AchievementGroupPath)

    suspend fun loadMultiText(locale: WwLocale): WwMultiText = readJsonResource(WwmResourcesPaths.multiTextPath(locale))

    @OptIn(ExperimentalResourceApi::class)
    private suspend inline fun <reified T> readJsonResource(path: String): T =
        Res.readBytes(path).decodeToString().let(Json::decodeFromString)
}

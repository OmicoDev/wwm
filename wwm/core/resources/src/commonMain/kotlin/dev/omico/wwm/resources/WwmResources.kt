/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.resources

import dev.omico.wwm.resources.generated.Res
import dev.omico.wwm.resources.model.game.WwLocale
import dev.omico.wwm.resources.model.game.WwMultiText
import dev.omico.wwm.resources.runtime.WwmResourcesPaths
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi

object WwmResources {
    suspend fun loadMultiText(locale: WwLocale): WwMultiText = readJsonResource(WwmResourcesPaths.multiTextPath(locale))

    @OptIn(ExperimentalResourceApi::class)
    private suspend inline fun <reified T> readJsonResource(path: String): T =
        Res.readBytes(path).decodeToString().let(Json::decodeFromString)
}

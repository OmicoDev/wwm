/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.data.internal

import dev.omico.wwm.data.WwmMarkedAchievementIds
import dev.omico.wwm.foundation.wwmMarkedAchievementsFile
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.readText
import kotlin.io.path.writeText

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING") // TODO KT-61573
internal actual class MarkedAchievementsDataStore {
    actual suspend fun load(): WwmMarkedAchievementIds =
        wwmMarkedAchievementsFile.takeIf(Path::exists)?.readText()?.fromJson<WwmMarkedAchievementIds>() ?: emptySet()

    actual suspend fun save(ids: WwmMarkedAchievementIds) {
        wwmMarkedAchievementsFile.writeText(ids.toJson())
    }
}

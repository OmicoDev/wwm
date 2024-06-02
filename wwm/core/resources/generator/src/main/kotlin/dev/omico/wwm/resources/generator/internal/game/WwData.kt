/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.resources.generator.internal.game

import dev.omico.wwm.resources.generator.internal.fromJson
import dev.omico.wwm.resources.model.game.WwAchievementCategories
import dev.omico.wwm.resources.model.game.WwAchievementGroups
import dev.omico.wwm.resources.model.game.WwAchievements
import dev.omico.wwm.resources.model.game.WwLocale
import dev.omico.wwm.resources.model.game.WwMultiText
import java.nio.file.Path

internal class WwData(private val gameDataDirectory: Path) {
    val achievements: WwAchievements = gameDataDirectory.resolve("BinData/achievement/achievement.json").fromJson()
    val achievementCategories: WwAchievementCategories =
        gameDataDirectory.resolve("BinData/achievement/achievementcategory.json").fromJson()
    val achievementGroups: WwAchievementGroups =
        gameDataDirectory.resolve("BinData/achievement/achievementgroup.json").fromJson()
    val multiTextMap: Map<WwLocale, WwMultiText> =
        WwLocale.entries.associateWith { locale ->
            gameDataDirectory.resolve("Textmaps/$locale/multi_text/MultiText.json").fromJson()
        }
}

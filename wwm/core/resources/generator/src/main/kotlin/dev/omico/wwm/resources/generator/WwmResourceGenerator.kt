/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
@file:JvmName("WwmResourceGenerator")

package dev.omico.wwm.resources.generator

import dev.omico.wwm.resources.generator.internal.game.WwData
import dev.omico.wwm.resources.generator.internal.writeTo
import dev.omico.wwm.resources.model.game.WwAchievement
import dev.omico.wwm.resources.model.game.WwAchievementCategory
import dev.omico.wwm.resources.model.game.WwAchievementGroup
import dev.omico.wwm.resources.model.game.WwText
import dev.omico.wwm.resources.runtime.WwmResourcesPaths
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.createParentDirectories

fun main(arguments: Array<String>) {
    val gameDataDirectory = Path(arguments[0])
    val outputDirectory = Path(arguments[1])
    WwData(gameDataDirectory).run {
        generateMultiTextFiles(outputDirectory)
        saveAchievementFiles(outputDirectory)
    }
}

private fun WwData.generateMultiTextFiles(outputDirectory: Path) {
    val textIds: MutableSet<String> = mutableSetOf<String>().apply {
        achievements.forEach { achievement ->
            add(achievement.name)
            add(achievement.desc)
        }
        achievementCategories.forEach { achievementCategory ->
            add(achievementCategory.name)
        }
        achievementGroups.forEach { achievementGroup ->
            add(achievementGroup.name)
        }
    }
    multiTextMap.forEach { (locale, multiText) ->
        multiText
            .filter { text -> text.id in textIds }
            .sortedBy(WwText::id)
            .saveToFile(outputDirectory, WwmResourcesPaths.multiTextPath(locale))
    }
}

private fun WwData.saveAchievementFiles(outputDirectory: Path) {
    achievements
        .sortedBy(WwAchievement::id)
        .saveToFile(outputDirectory, WwmResourcesPaths.AchievementPath)
    achievementCategories
        // For achievements
        .filter { category -> category.functionType == 1 }
        .sortedBy(WwAchievementCategory::id)
        .saveToFile(outputDirectory, WwmResourcesPaths.AchievementCategoryPath)
    achievementGroups
        .sortedWith(compareBy(WwAchievementGroup::id, WwAchievementGroup::sort))
        .saveToFile(outputDirectory, WwmResourcesPaths.AchievementGroupPath)
}

private inline fun <reified T> T.saveToFile(outputDirectory: Path, path: String) =
    writeTo(outputDirectory.resolve(path).createParentDirectories())

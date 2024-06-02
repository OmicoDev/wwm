/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
@file:JvmName("WwmResourceGenerator")

package dev.omico.wwm.resources.generator

import dev.omico.wwm.resources.generator.internal.game.WwData
import dev.omico.wwm.resources.generator.internal.writeTo
import dev.omico.wwm.resources.model.game.WwText
import dev.omico.wwm.resources.runtime.WwmResourcesPaths
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.createParentDirectories

fun main(arguments: Array<String>) {
    val gameDataDirectory = Path(arguments[0])
    val outputDirectory = Path(arguments[1])
    val wwData = WwData(gameDataDirectory)
    wwData.generateMultiTextFiles(outputDirectory)
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
        val outputMultiTextFile = outputDirectory.resolve(WwmResourcesPaths.multiTextPath(locale))
            .createParentDirectories()
        multiText
            .filter { it.id in textIds }
            .sortedBy(WwText::id)
            .writeTo(outputMultiTextFile)
    }
}

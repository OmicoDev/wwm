/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.data

import dev.omico.wwm.resources.model.game.WwAchievementCategories
import dev.omico.wwm.resources.model.game.WwAchievementGroups
import dev.omico.wwm.resources.model.game.WwAchievements
import dev.omico.wwm.resources.model.game.WwLocale
import dev.omico.wwm.resources.model.game.WwMultiText

data class WwmAchievements(
    val achievements: WwAchievements = emptyList(),
    val achievementCategories: WwAchievementCategories = emptyList(),
    val achievementGroups: WwAchievementGroups = emptyList(),
    val multiText: WwMultiText = emptyList(),
    val locale: WwLocale = WwLocale.ZH_HANS,
) {
    companion object {
        val Empty: WwmAchievements = WwmAchievements()
    }
}

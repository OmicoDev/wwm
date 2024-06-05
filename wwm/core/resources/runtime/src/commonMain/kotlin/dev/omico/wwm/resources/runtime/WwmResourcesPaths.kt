/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.resources.runtime

import dev.omico.wwm.resources.model.game.WwLocale

@Suppress("ConstPropertyName")
object WwmResourcesPaths {
    const val AchievementPath: String = "files/Achievement.json"
    const val AchievementCategoryPath: String = "files/AchievementCategory.json"
    const val AchievementGroupPath: String = "files/AchievementGroup.json"
    fun multiTextPath(locale: WwLocale): String = "files/MultiText.$locale.json"
}

/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap

@Stable
internal class AchievementCategoriesToggleState {
    private val categoryToggleStateMap: SnapshotStateMap<Int, Boolean> = mutableStateMapOf()

    fun isExpanded(id: Int): Boolean = categoryToggleStateMap.getOrPut(id) { false }

    fun toggle(id: Int) {
        categoryToggleStateMap[id] = isExpanded(id).not()
    }

    fun collapseAll() {
        categoryToggleStateMap.keys.forEach { key ->
            categoryToggleStateMap[key] = false
        }
    }

    fun expandAll() {
        categoryToggleStateMap.keys.forEach { key ->
            categoryToggleStateMap[key] = true
        }
    }
}

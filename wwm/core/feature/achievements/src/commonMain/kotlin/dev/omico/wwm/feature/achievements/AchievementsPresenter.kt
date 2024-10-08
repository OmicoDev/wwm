/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.slack.circuit.retained.collectAsRetainedState
import dev.omico.wwm.resources.model.game.WwLocale
import dev.omico.wwm.ui.WwmUiComponent
import dev.omico.wwm.ui.rememberUpdatedListState
import kotlinx.coroutines.launch

@Composable
internal fun WwmUiComponent.produceAchievementsUiState(): AchievementsUiState {
    val scope = rememberCoroutineScope()
    val achievements by achievementsRepository.achievements.collectAsRetainedState(initial = emptyList())
    val achievementCategories
        by achievementsRepository.achievementCategories.collectAsRetainedState(initial = emptyList())
    val achievementGroups
        by achievementsRepository.achievementGroups.collectAsRetainedState(initial = emptyList())
    val multiText by achievementsRepository.multiText.collectAsRetainedState(initial = emptyList())
    val markedAchievementIds by achievementsRepository.markedAchievementIds.collectAsRetainedState(initial = emptySet())
    var locale by remember { mutableStateOf(WwLocale.ZH_HANS) }
    LaunchedEffect(Unit) { achievementsRepository.load() }
    LaunchedEffect(locale) { achievementsRepository.reloadMultiText(locale) }
    return AchievementsUiState(
        achievements = rememberUpdatedListState(achievements),
        achievementCategories = rememberUpdatedListState(achievementCategories),
        achievementGroups = rememberUpdatedListState(achievementGroups),
        multiText = rememberUpdatedListState(multiText),
        locale = locale,
        markedAchievementIds = rememberUpdatedListState(markedAchievementIds),
        eventSink = { event ->
            when (event) {
                is AchievementsUiEvent.ChangeLocale -> locale = event.locale
                is AchievementsUiEvent.ChangeAchievementMark ->
                    scope.launch {
                        achievementsRepository.markAchievement(
                            marked = event.marked,
                            achievementId = event.achievementId,
                        )
                    }
                is AchievementsUiEvent.ExportData ->
                    scope.launch { event.onExported(achievementsRepository.exportData()) }
                is AchievementsUiEvent.ImportData ->
                    scope.launch { achievementsRepository.importData(event.json) }
            }
        },
    )
}

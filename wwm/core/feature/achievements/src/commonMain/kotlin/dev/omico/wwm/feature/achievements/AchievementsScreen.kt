/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements

import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.presenterOf
import com.slack.circuit.runtime.screen.Screen
import dev.omico.wwm.ui.LocalWwmUiComponent
import dev.omico.wwm.ui.theme.WwmTheme

object AchievementsScreen : Screen

fun Circuit.Builder.addAchievementsFeature(): Circuit.Builder =
    apply {
        addUi<AchievementsScreen, AchievementsUiState> { state, modifier ->
            WwmTheme(
                locale = state.locale,
                content = {
                    AchievementsUi(
                        state = state,
                        modifier = modifier,
                    )
                },
            )
        }
        addPresenter<AchievementsScreen, AchievementsUiState> { _, _, _ ->
            presenterOf {
                with(LocalWwmUiComponent.current) {
                    produceAchievementsUiState()
                }
            }
        }
    }

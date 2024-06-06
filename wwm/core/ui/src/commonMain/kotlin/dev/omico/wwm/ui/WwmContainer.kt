/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.ui

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.LocalCircuit
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.retained.LocalRetainedStateRegistry
import com.slack.circuit.retained.continuityRetainedStateRegistry
import com.slack.circuit.runtime.screen.Screen

@OptIn(
    ExperimentalMaterial3AdaptiveApi::class,
    ExperimentalMaterial3AdaptiveNavigationSuiteApi::class,
)
@Composable
fun WwmContainer(
    circuit: Circuit,
    initialScreen: Screen,
) {
    val windowAdaptiveInfo = currentWindowAdaptiveInfo()
    val navigationSuiteType = NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(windowAdaptiveInfo)
    CompositionLocalProvider(
        LocalNavigationSuiteType provides navigationSuiteType,
        LocalRetainedStateRegistry provides continuityRetainedStateRegistry(),
        LocalCircuit provides circuit,
        content = {
            val backStack = rememberSaveableBackStack(initialScreens = listOf(initialScreen))
            val navigator = rememberCircuitNavigator(
                backStack = backStack,
                onRootPop = {},
            )
            NavigableCircuitContent(
                navigator = navigator,
                backStack = backStack,
            )
        },
    )
}

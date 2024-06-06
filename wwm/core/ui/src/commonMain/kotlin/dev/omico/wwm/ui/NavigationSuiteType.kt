/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.ui

import androidx.compose.material3.adaptive.navigationsuite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
val LocalNavigationSuiteType: ProvidableCompositionLocal<NavigationSuiteType> =
    staticCompositionLocalOf { error("No NavigationSuiteType provided") }

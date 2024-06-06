/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.application

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.navigationsuite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
@Composable
fun WwmApp() {
    MaterialTheme {
        WwmContainer()
    }
}

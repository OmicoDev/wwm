/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.material3.adaptive.layout

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A Material opinionated implementation of [ThreePaneScaffold] that will display the provided three
 * panes in a canonical supporting-pane layout.
 *
 * @param supportingPane the supporting pane of the scaffold.
 *        See [SupportingPaneScaffoldRole.Supporting].
 * @param modifier [Modifier] of the scaffold layout.
 * @param directive The top-level directives about how the scaffold should arrange its panes.
 * @param value The current adapted value of the scaffold, which indicates how each pane of
 *        the scaffold is adapted.
 * @param windowInsets window insets that the scaffold will respect.
 * @param extraPane the extra pane of the scaffold. See [SupportingPaneScaffoldRole.Extra].
 * @param mainPane the main pane of the scaffold. See [SupportingPaneScaffoldRole.Main].
 */
@ExperimentalMaterial3AdaptiveApi
@Composable
fun SupportingPaneScaffold(
    supportingPane: @Composable ThreePaneScaffoldScope.() -> Unit,
    modifier: Modifier = Modifier,
    directive: PaneScaffoldDirective =
        calculateStandardPaneScaffoldDirective(currentWindowAdaptiveInfo()),
    value: ThreePaneScaffoldValue = calculateThreePaneScaffoldValue(
        directive.maxHorizontalPartitions,
        SupportingPaneScaffoldDefaults.adaptStrategies(),
        ThreePaneScaffoldDestinationItem<Nothing>(SupportingPaneScaffoldRole.Main)
    ),
    windowInsets: WindowInsets = SupportingPaneScaffoldDefaults.windowInsets,
    extraPane: (@Composable ThreePaneScaffoldScope.() -> Unit)? = null,
    mainPane: @Composable ThreePaneScaffoldScope.() -> Unit,
) {
    ThreePaneScaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldDirective = directive,
        scaffoldValue = value,
        paneOrder = ThreePaneScaffoldDefaults.SupportingPaneLayoutPaneOrder,
        windowInsets = windowInsets,
        secondaryPane = supportingPane,
        tertiaryPane = extraPane,
        primaryPane = mainPane
    )
}

/**
 * Provides default values of [SupportingPaneScaffold].
 */
@ExperimentalMaterial3AdaptiveApi
object SupportingPaneScaffoldDefaults {
    /**
     * Default insets that will be used and consumed by [SupportingPaneScaffold]. By default it will
     * be the union of [WindowInsets.Companion.systemBars] and
     * [WindowInsets.Companion.displayCutout].
     */
    val windowInsets @Composable get() = WindowInsets.systemBars.union(WindowInsets.displayCutout)

    /**
     * Creates a default [ThreePaneScaffoldAdaptStrategies] for [SupportingPaneScaffold].
     *
     * @param mainPaneAdaptStrategy the adapt strategy of the main pane
     * @param supportingPaneAdaptStrategy the adapt strategy of the supporting pane
     * @param extraPaneAdaptStrategy the adapt strategy of the extra pane
     */
    fun adaptStrategies(
        mainPaneAdaptStrategy: AdaptStrategy = AdaptStrategy.Hide,
        supportingPaneAdaptStrategy: AdaptStrategy = AdaptStrategy.Hide,
        extraPaneAdaptStrategy: AdaptStrategy = AdaptStrategy.Hide,
    ): ThreePaneScaffoldAdaptStrategies =
        ThreePaneScaffoldAdaptStrategies(
            mainPaneAdaptStrategy,
            supportingPaneAdaptStrategy,
            extraPaneAdaptStrategy
        )
}

/**
 * The set of the available pane roles of [SupportingPaneScaffold]. Basically those values are
 * aliases of [ThreePaneScaffoldRole]. We suggest you to use the values defined here instead of
 * the raw [ThreePaneScaffoldRole] under the context of [SupportingPaneScaffold] for better
 * code clarity.
 */
@ExperimentalMaterial3AdaptiveApi
object SupportingPaneScaffoldRole {
    /**
     * The main pane of [SupportingPaneScaffold]. It is an alias of
     * [ThreePaneScaffoldRole.Primary].
     */
    val Main = ThreePaneScaffoldRole.Primary

    /**
     * The supporting pane of [SupportingPaneScaffold]. It is an alias of
     * [ThreePaneScaffoldRole.Secondary].
     */
    val Supporting = ThreePaneScaffoldRole.Secondary

    /**
     * The extra pane of [SupportingPaneScaffold]. It is an alias of
     * [ThreePaneScaffoldRole.Tertiary].
     */
    val Extra = ThreePaneScaffoldRole.Tertiary
}

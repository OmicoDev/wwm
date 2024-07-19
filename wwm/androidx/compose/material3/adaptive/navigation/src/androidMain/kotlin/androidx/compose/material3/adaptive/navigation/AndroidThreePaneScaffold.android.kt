/*
 * Copyright 2024 The Android Open Source Project
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

package androidx.compose.material3.adaptive.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold as BaseListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold as BaseSupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A version of [androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold] that supports
 * navigation and back handling out of the box, controlled by [ThreePaneScaffoldNavigator].
 *
 * @param navigator The navigator instance to navigate through the scaffold.
 * @param listPane the list pane of the scaffold, which is supposed to hold a list of item summaries
 *   that can be selected from, for example, the inbox mail list of a mail app. See
 *   [ListDetailPaneScaffoldRole.List].
 * @param detailPane the detail pane of the scaffold, which is supposed to hold the detailed info of
 *   a selected item, for example, the mail content currently being viewed. See
 *   [ListDetailPaneScaffoldRole.Detail].
 * @param modifier [Modifier] of the scaffold layout.
 * @param extraPane the extra pane of the scaffold, which is supposed to hold any supplementary info
 *   besides the list and the detail panes, for example, a task list or a mini-calendar view of a
 *   mail app. See [ListDetailPaneScaffoldRole.Extra].
 * @param defaultBackBehavior the default back navigation behavior when the system back event
 *   happens. See [BackNavigationBehavior] for the use cases of each behavior.
 */
@ExperimentalMaterial3AdaptiveApi
@Composable
fun NavigableListDetailPaneScaffold(
    navigator: ThreePaneScaffoldNavigator<Any>,
    listPane: @Composable ThreePaneScaffoldScope.() -> Unit,
    detailPane: @Composable ThreePaneScaffoldScope.() -> Unit,
    modifier: Modifier = Modifier,
    extraPane: (@Composable ThreePaneScaffoldScope.() -> Unit)? = null,
    defaultBackBehavior: BackNavigationBehavior = BackNavigationBehavior.PopUntilContentChange,
) {
    // TODO(b/330584029): support predictive back
    BackHandler(enabled = navigator.canNavigateBack(defaultBackBehavior)) {
        navigator.navigateBack(defaultBackBehavior)
    }
    BaseListDetailPaneScaffold(
        modifier = modifier,
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        detailPane = detailPane,
        listPane = listPane,
        extraPane = extraPane
    )
}

/**
 * A version of [androidx.compose.material3.adaptive.layout.SupportingPaneScaffold] that supports
 * navigation and back handling out of the box, controlled by [ThreePaneScaffoldNavigator].
 *
 * @param navigator The navigator instance to navigate through the scaffold.
 * @param mainPane the main pane of the scaffold, which is supposed to hold the major content of an
 *   app, for example, the editing screen of a doc app. See [SupportingPaneScaffoldRole.Main].
 * @param supportingPane the supporting pane of the scaffold, which is supposed to hold the support
 *   content of an app, for example, the comment list of a doc app. See
 *   [SupportingPaneScaffoldRole.Supporting].
 * @param modifier [Modifier] of the scaffold layout.
 * @param extraPane the extra pane of the scaffold, which is supposed to hold any additional content
 *   besides the main and the supporting panes, for example, a styling panel in a doc app. See
 *   [SupportingPaneScaffoldRole.Extra].
 * @param defaultBackBehavior the default back navigation behavior when the system back event
 *   happens. See [BackNavigationBehavior] for the use cases of each behavior.
 */
@ExperimentalMaterial3AdaptiveApi
@Composable
fun NavigableSupportingPaneScaffold(
    navigator: ThreePaneScaffoldNavigator<Any>,
    mainPane: @Composable ThreePaneScaffoldScope.() -> Unit,
    supportingPane: @Composable ThreePaneScaffoldScope.() -> Unit,
    modifier: Modifier = Modifier,
    extraPane: (@Composable ThreePaneScaffoldScope.() -> Unit)? = null,
    defaultBackBehavior: BackNavigationBehavior = BackNavigationBehavior.PopUntilContentChange,
) {
    // TODO(b/330584029): support predictive back
    BackHandler(enabled = navigator.canNavigateBack(defaultBackBehavior)) {
        navigator.navigateBack(defaultBackBehavior)
    }
    BaseSupportingPaneScaffold(
        modifier = modifier,
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = mainPane,
        supportingPane = supportingPane,
        extraPane = extraPane
    )
}

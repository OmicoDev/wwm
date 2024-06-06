/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldScope
import androidx.compose.material3.adaptive.navigationsuite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.omico.wwm.ui.LocalNavigationSuiteType

@Composable
internal fun AchievementsUi(
    state: AchievementsUiState,
    modifier: Modifier = Modifier,
) {
    AchievementsScaffold(
        titleText = state.title,
        listPane = { Box(modifier = Modifier.background(color = Color.LightGray)) },
        detailPane = { Box(modifier = Modifier.background(color = Color.DarkGray)) },
        modifier = modifier,
    )
}

@OptIn(
    ExperimentalMaterial3AdaptiveApi::class,
    ExperimentalMaterial3AdaptiveNavigationSuiteApi::class,
    ExperimentalMaterial3Api::class,
)
@Composable
private fun AchievementsScaffold(
    titleText: String,
    listPane: @Composable ThreePaneScaffoldScope.() -> Unit,
    detailPane: @Composable ThreePaneScaffoldScope.() -> Unit,
    modifier: Modifier = Modifier,
) {
    val navigationSuiteType = LocalNavigationSuiteType.current
    Column(modifier = modifier) {
        if (navigationSuiteType == NavigationSuiteType.NavigationRail) {
            Text(
                text = titleText,
                modifier = Modifier.padding(all = 16.dp),
                style = MaterialTheme.typography.headlineSmall,
            )
        }
        NavigationSuiteScaffold(
            navigationSuiteItems = {
                item(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Email,
                            contentDescription = null,
                        )
                    },
                )
            },
            content = {
                Scaffold(
                    modifier = modifier,
                    topBar = {
                        if (navigationSuiteType == NavigationSuiteType.NavigationBar) {
                            TopAppBar(title = { Text(text = titleText) })
                        }
                    },
                    content = {
                        ListDetailPaneScaffold(
                            listPane = listPane,
                            detailPane = detailPane,
                        )
                    },
                )
            },
        )
    }
}

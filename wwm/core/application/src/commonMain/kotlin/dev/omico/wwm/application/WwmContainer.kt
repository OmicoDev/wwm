/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.application

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
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigationsuite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(
    ExperimentalMaterial3AdaptiveApi::class,
    ExperimentalMaterial3AdaptiveNavigationSuiteApi::class,
    ExperimentalMaterial3Api::class,
)
@Composable
fun WwmContainer(
    navigationSuiteType: NavigationSuiteType = run {
        NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(currentWindowAdaptiveInfo())
    },
) {
    val titleText = "Title"
    Column {
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
                    topBar = {
                        if (navigationSuiteType == NavigationSuiteType.NavigationBar) {
                            TopAppBar(title = { Text(text = titleText) })
                        }
                    },
                    content = {
                        ListDetailPaneScaffold(
                            listPane = {
                                Box(modifier = Modifier.background(Color.LightGray))
                            },
                            detailPane = {
                                Box(modifier = Modifier.background(Color.DarkGray))
                            },
                        )
                    },
                )
            },
        )
    }
}

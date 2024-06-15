/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun AchievementsDetailItem(
    marked: Boolean,
    onMarkedChange: (Boolean) -> Unit,
    name: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineContent = { Text(text = name) },
        modifier = modifier,
        supportingContent = { Text(text = description) },
        trailingContent = {
            Checkbox(
                checked = marked,
                onCheckedChange = onMarkedChange,
            )
        },
    )
}

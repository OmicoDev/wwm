/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun AchievementsDetailItem(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        content = {
            Box(modifier = Modifier.padding(all = 16.dp)) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        },
    )
}

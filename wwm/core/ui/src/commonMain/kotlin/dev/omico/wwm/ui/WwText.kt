/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import dev.omico.wwm.resources.model.game.WwMultiText

@Composable
fun rememberWwText(multiText: WwMultiText, name: String): String {
    val currentMultiText = rememberUpdatedListState(multiText)
    val text by remember(currentMultiText, name) {
        derivedStateOf {
            currentMultiText.firstOrNull { it.id == name }?.content ?: name
        }
    }
    return text
}

/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.ui.theme

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.omico.wwm.resources.model.game.WwLocale
import dev.omico.wwm.ui.foundation.Typography

@Composable
fun WwmTheme(
    locale: WwLocale = LocalWwmLocale.current,
    content: @Composable () -> Unit,
) {
    var isInitialized by remember { mutableStateOf(false) }
    MaterialTheme(
        typography = run {
            when (locale) {
                WwLocale.ZH_HANS ->
                    Typography(
                        loadFontFamily = { loadNotoSansSCFontFamily() },
                        onLoaded = { isInitialized = true },
                    )
                else -> MaterialTheme.typography.also { isInitialized = true }
            }
        },
        content = {
            Crossfade(targetState = isInitialized) { isInitialized ->
                when {
                    isInitialized -> content()
                    else ->
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            content = {
                                CircularProgressIndicator()
                                Spacer(modifier = Modifier.height(height = 16.dp))
                                Text("Loading...")
                            },
                        )
                }
            }
        },
    )
}

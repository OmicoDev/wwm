/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.ui.foundation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontFamily
import kotlin.coroutines.CoroutineContext

@Composable
@ReadOnlyComposable
fun Typography(fontFamily: FontFamily): Typography =
    MaterialTheme.typography.copy(
        displayLarge = MaterialTheme.typography.displayLarge.copy(fontFamily = fontFamily),
        displayMedium = MaterialTheme.typography.displayMedium.copy(fontFamily = fontFamily),
        displaySmall = MaterialTheme.typography.displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = MaterialTheme.typography.headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = MaterialTheme.typography.headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = MaterialTheme.typography.headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = MaterialTheme.typography.titleLarge.copy(fontFamily = fontFamily),
        titleMedium = MaterialTheme.typography.titleMedium.copy(fontFamily = fontFamily),
        titleSmall = MaterialTheme.typography.titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = MaterialTheme.typography.bodySmall.copy(fontFamily = fontFamily),
        labelLarge = MaterialTheme.typography.labelLarge.copy(fontFamily = fontFamily),
        labelMedium = MaterialTheme.typography.labelMedium.copy(fontFamily = fontFamily),
        labelSmall = MaterialTheme.typography.labelSmall.copy(fontFamily = fontFamily),
    )

@Composable
fun Typography(
    loadFontFamily: suspend CoroutineContext.() -> FontFamily,
    onLoaded: suspend CoroutineContext.() -> Unit,
): Typography {
    var fontFamily: FontFamily? by remember { mutableStateOf(null) }
    LaunchedEffect(Unit) {
        fontFamily = loadFontFamily(coroutineContext)
        onLoaded(coroutineContext)
    }
    return Typography(fontFamily = fontFamily ?: return MaterialTheme.typography)
}

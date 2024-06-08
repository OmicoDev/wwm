/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dev.omico.wwm.resources.model.game.WwLocale

@Composable
fun WwmTheme(
    locale: WwLocale = LocalWwmLocale.current,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        typography = when (locale) {
            WwLocale.ZH_HANS -> NotoSansSCTypography()
            else -> MaterialTheme.typography
        },
        content = content,
    )
}

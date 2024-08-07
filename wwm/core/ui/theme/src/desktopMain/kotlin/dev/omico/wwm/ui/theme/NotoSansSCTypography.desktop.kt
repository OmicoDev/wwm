/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import dev.omico.wwm.resources.WwmFonts

internal actual suspend fun loadNotoSansSCFontFamily(): FontFamily =
    FontFamily(
        Font(
            identity = "NotoSansSC_Medium",
            data = WwmFonts.notoSansSCMedium(),
            weight = FontWeight.Medium,
        ),
        Font(
            identity = "NotoSansSC_Regular",
            data = WwmFonts.notoSansSCRegular(),
            weight = FontWeight.Normal,
        ),
    )

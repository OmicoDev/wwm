/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import dev.omico.wwm.resources.WwmFonts

internal suspend fun loadNotoSansSCFontFamily(): FontFamily =
    FontFamily(
        Font(
            identity = "NotoSansSC_Black",
            data = WwmFonts.notoSansSCBlack(),
            weight = FontWeight.Black,
        ),
        Font(
            identity = "NotoSansSC_Bold",
            data = WwmFonts.notoSansSCBold(),
            weight = FontWeight.Bold,
        ),
        Font(
            identity = "NotoSansSC_ExtraBold",
            data = WwmFonts.notoSansSCExtraBold(),
            weight = FontWeight.ExtraBold,
        ),
        Font(
            identity = "NotoSansSC_ExtraLight",
            data = WwmFonts.notoSansSCExtraLight(),
            weight = FontWeight.ExtraLight,
        ),
        Font(
            identity = "NotoSansSC_Light",
            data = WwmFonts.notoSansSCLight(),
            weight = FontWeight.Light,
        ),
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
        Font(
            identity = "NotoSansSC_SemiBold",
            data = WwmFonts.notoSansSCSemiBold(),
            weight = FontWeight.SemiBold,
        ),
        Font(
            identity = "NotoSansSC_Thin",
            data = WwmFonts.notoSansSCThin(),
            weight = FontWeight.Thin,
        ),
    )

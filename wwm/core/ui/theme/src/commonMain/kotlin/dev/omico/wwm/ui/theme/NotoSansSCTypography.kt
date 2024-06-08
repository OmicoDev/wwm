/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import dev.omico.wwm.resources.WwmFonts
import dev.omico.wwm.resources.model.game.WwLocale
import org.jetbrains.compose.resources.Font

val LocalWwmLocale: ProvidableCompositionLocal<WwLocale> =
    staticCompositionLocalOf { error("No LocalWwmLocale provided") }

@Composable
internal fun NotoSansSCTypography(typography: Typography = MaterialTheme.typography): Typography {
    val notoSansSC = FontFamily_NotoSansSC()
    return typography.copy(
        displayLarge = typography.displayLarge.copy(fontFamily = notoSansSC),
        displayMedium = typography.displayMedium.copy(fontFamily = notoSansSC),
        displaySmall = typography.displaySmall.copy(fontFamily = notoSansSC),
        headlineLarge = typography.headlineLarge.copy(fontFamily = notoSansSC),
        headlineMedium = typography.headlineMedium.copy(fontFamily = notoSansSC),
        headlineSmall = typography.headlineSmall.copy(fontFamily = notoSansSC),
        titleLarge = typography.titleLarge.copy(fontFamily = notoSansSC),
        titleMedium = typography.titleMedium.copy(fontFamily = notoSansSC),
        titleSmall = typography.titleSmall.copy(fontFamily = notoSansSC),
        bodyLarge = typography.bodyLarge.copy(fontFamily = notoSansSC),
        bodyMedium = typography.bodyMedium.copy(fontFamily = notoSansSC),
        bodySmall = typography.bodySmall.copy(fontFamily = notoSansSC),
        labelLarge = typography.labelLarge.copy(fontFamily = notoSansSC),
        labelMedium = typography.labelMedium.copy(fontFamily = notoSansSC),
        labelSmall = typography.labelSmall.copy(fontFamily = notoSansSC),
    )
}

@Composable
private fun FontFamily_NotoSansSC(): FontFamily =
    FontFamily(
        Font(
            resource = WwmFonts.NotoSansSC_Black,
            weight = FontWeight.Black,
        ),
        Font(
            resource = WwmFonts.NotoSansSC_Bold,
            weight = FontWeight.Bold,
        ),
        Font(
            resource = WwmFonts.NotoSansSC_ExtraBold,
            weight = FontWeight.ExtraBold,
        ),
        Font(
            resource = WwmFonts.NotoSansSC_ExtraLight,
            weight = FontWeight.ExtraLight,
        ),
        Font(
            resource = WwmFonts.NotoSansSC_Light,
            weight = FontWeight.Light,
        ),
        Font(
            resource = WwmFonts.NotoSansSC_Medium,
            weight = FontWeight.Medium,
        ),
        Font(
            resource = WwmFonts.NotoSansSC_Regular,
            weight = FontWeight.Normal,
        ),
        Font(
            resource = WwmFonts.NotoSansSC_SemiBold,
            weight = FontWeight.SemiBold,
        ),
        Font(
            resource = WwmFonts.NotoSansSC_Regular,
            weight = FontWeight.Thin,
        ),
    )

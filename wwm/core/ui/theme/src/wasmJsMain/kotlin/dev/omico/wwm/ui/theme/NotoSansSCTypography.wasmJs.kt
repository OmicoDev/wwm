/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import dev.omico.wwm.resources.WwmFonts
import kotlinx.browser.localStorage
import kotlinx.coroutines.coroutineScope
import org.w3c.dom.get
import org.w3c.dom.set
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

internal actual suspend fun loadNotoSansSCFontFamily(): FontFamily =
    FontFamily(
        loadFontFamilyFromLocalStorage(
            identity = "NotoSansSC_Medium",
            loadFontBytes = WwmFonts::notoSansSCMedium,
            weight = FontWeight.Medium,
        ),
        loadFontFamilyFromLocalStorage(
            identity = "NotoSansSC_Regular",
            loadFontBytes = WwmFonts::notoSansSCRegular,
            weight = FontWeight.Normal,
        ),
    )

@OptIn(ExperimentalEncodingApi::class)
private suspend fun loadFontFamilyFromLocalStorage(
    identity: String,
    loadFontBytes: suspend () -> ByteArray,
    weight: FontWeight,
): Font {
    val localStorageKey = "font_$identity"
    val fontBase64 = localStorage[localStorageKey]
    val fontByteArray = when {
        fontBase64.isNullOrBlank() ->
            coroutineScope { loadFontBytes().also { bytes -> localStorage[localStorageKey] = Base64.encode(bytes) } }
        else -> Base64.decode(fontBase64)
    }
    return Font(
        identity = identity,
        data = fontByteArray,
        weight = weight,
    )
}

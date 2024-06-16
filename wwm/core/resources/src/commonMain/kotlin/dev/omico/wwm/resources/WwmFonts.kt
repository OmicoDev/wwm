/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.resources

import dev.omico.wwm.resources.generated.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
object WwmFonts {
    suspend fun notoSansSCBlack(): ByteArray = Res.readBytes("font/NotoSansSC-Black.ttf")
    suspend fun notoSansSCBold(): ByteArray = Res.readBytes("font/NotoSansSC-Bold.ttf")
    suspend fun notoSansSCExtraBold(): ByteArray = Res.readBytes("font/NotoSansSC-ExtraBold.ttf")
    suspend fun notoSansSCExtraLight(): ByteArray = Res.readBytes("font/NotoSansSC-ExtraLight.ttf")
    suspend fun notoSansSCLight(): ByteArray = Res.readBytes("font/NotoSansSC-Light.ttf")
    suspend fun notoSansSCMedium(): ByteArray = Res.readBytes("font/NotoSansSC-Medium.ttf")
    suspend fun notoSansSCRegular(): ByteArray = Res.readBytes("font/NotoSansSC-Regular.ttf")
    suspend fun notoSansSCSemiBold(): ByteArray = Res.readBytes("font/NotoSansSC-SemiBold.ttf")
    suspend fun notoSansSCThin(): ByteArray = Res.readBytes("font/NotoSansSC-Thin.ttf")
}

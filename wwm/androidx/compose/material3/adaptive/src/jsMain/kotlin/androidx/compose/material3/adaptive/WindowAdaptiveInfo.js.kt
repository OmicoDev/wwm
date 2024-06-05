package androidx.compose.material3.adaptive

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.IntSize

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal actual fun currentWindowSize(): IntSize = LocalWindowInfo.current.containerSize

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal actual fun calculatePosture(): Posture = Posture()

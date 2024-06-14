/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.resources

import dev.omico.wwm.resources.generated.Res
import dev.omico.wwm.resources.generated.collapse_all
import dev.omico.wwm.resources.generated.expand_all
import dev.omico.wwm.resources.generated.export
import dev.omico.wwm.resources.generated.import
import dev.omico.wwm.resources.generated.import_export
import dev.omico.wwm.resources.generated.translate
import org.jetbrains.compose.resources.DrawableResource

object WwmIcons {
    val CollapseAll: DrawableResource = Res.drawable.collapse_all
    val ExpandAll: DrawableResource = Res.drawable.expand_all
    val Export: DrawableResource = Res.drawable.export
    val Import: DrawableResource = Res.drawable.import
    val ImportExport: DrawableResource = Res.drawable.import_export
    val Translate: DrawableResource = Res.drawable.translate
}

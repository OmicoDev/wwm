/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.platform

import java.awt.FileDialog
import java.awt.Frame
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText

internal actual fun importData(import: (String) -> Unit) {
    FileDialog(Frame(), "Load from...").run {
        mode = FileDialog.LOAD
        isVisible = true
        dispose()
        val directory = directory?.let(::Path) ?: return
        import(file?.let(directory::resolve)?.readText() ?: "")
    }
}

internal actual fun exportData(json: String) {
    FileDialog(Frame(), "Save to...").run {
        mode = FileDialog.SAVE
        file = "wwm-exported.json"
        isVisible = true
        dispose()
        val directory = directory?.let(::Path) ?: return
        file?.let(directory::resolve)?.writeText(json)
    }
}

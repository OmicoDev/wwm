/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.platform

internal expect fun importData(import: (String) -> Unit)

internal expect fun exportData(json: String)

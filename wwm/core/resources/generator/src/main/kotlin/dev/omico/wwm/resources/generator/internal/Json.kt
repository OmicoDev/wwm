/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.resources.generator.internal

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.file.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText

@OptIn(ExperimentalSerializationApi::class)
private val json: Json = Json {
    prettyPrint = true
    prettyPrintIndent = "  "
}

internal inline fun <reified T> Path.fromJson(): T = json.decodeFromString(readText())

internal inline fun <reified T> T.writeTo(path: Path): Unit = path.writeText(json.encodeToString(this))

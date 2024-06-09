/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.data.internal

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
internal val json: Json =
    Json {
        prettyPrint = true
        prettyPrintIndent = "  "
    }

internal inline fun <reified T> T.toJson(): String = json.encodeToString(this)

internal inline fun <reified T> String.fromJson(): T = json.decodeFromString(this)

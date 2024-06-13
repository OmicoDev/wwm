/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.platform

import kotlinx.browser.document
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.url.URL
import org.w3c.files.Blob
import org.w3c.files.BlobPropertyBag
import org.w3c.files.FileReader
import org.w3c.files.get
import kotlin.js.JsAny
import kotlin.js.JsArray
import kotlin.js.set
import kotlin.js.toJsString

internal actual fun importData(import: (String) -> Unit) {
    val fileInputElement = document.createElement("input") as HTMLInputElement
    fileInputElement.run {
        type = "file"
        accept = "application/json"
        addEventListener("change") {
            val file = fileInputElement.files?.get(0) ?: return@addEventListener
            FileReader().run {
                onload = { import(result?.toString() ?: "") }
                onerror = { println("Error reading file: $error") }
                readAsText(file)
            }
        }
    }
    document.body?.run {
        appendChild(fileInputElement)
        fileInputElement.click()
        removeChild(fileInputElement)
    }
}

internal actual fun exportData(json: String) {
    val blobContent = JsArray<JsAny?>()
    blobContent[0] = json.toJsString()
    val a = document.createElement("a") as? HTMLAnchorElement ?: return
    val url = URL.createObjectURL(
        blob = Blob(
            blobParts = blobContent,
            options = BlobPropertyBag("application/json"),
        ),
    )
    a.href = url
    a.download = "wwm-exported.json"
    document.body?.appendChild(a)
    a.click()
    document.body?.removeChild(a)
    URL.revokeObjectURL(url)
}

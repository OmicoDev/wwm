/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.resources.model.game

enum class WwLocale(private val value: String) {
    DE("de"),
    EN("en"),
    ES("es"),
    FR("fr"),
    ID("id"),
    JA("ja"),
    KO("ko"),
    PT("pt"),
    RU("ru"),
    TH("th"),
    VI("vi"),
    ZH_HANS("zh-Hans"),
    ZH_HANT("zh-Hant"),
    ;

    override fun toString(): String = value
}

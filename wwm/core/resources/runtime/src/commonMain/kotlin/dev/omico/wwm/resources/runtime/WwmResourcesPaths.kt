/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.resources.runtime

import dev.omico.wwm.resources.model.game.WwLocale

object WwmResourcesPaths {
    fun multiTextPath(locale: WwLocale): String = "files/MultiText.$locale.json"
}

/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.foundation

object DesktopApplicationEnvironments {
    private val environments: Map<String, String> = System.getenv()

    val WWM_LOCAL_DIRECTORY: String? = environments["WWM_LOCAL_DIRECTORY"]
}

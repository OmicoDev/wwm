/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.gradle.project

import org.gradle.api.Project

internal fun Project.configureCommonComposeMultiplatform(
    composeMultiplatformVersion: String,
): Unit =
    configurations.all {
        resolutionStrategy.eachDependency {
            val group = requested.group
            when (group) {
                in composeMultiplatformGroups -> useVersion(composeMultiplatformVersion)
            }
        }
    }

private val composeMultiplatformGroups: List<String> =
    listOf(
        "org.jetbrains.compose.animation",
        "org.jetbrains.compose.foundation",
        "org.jetbrains.compose.material",
        "org.jetbrains.compose.material3",
        "org.jetbrains.compose.runtime",
        "org.jetbrains.compose.ui",
    )

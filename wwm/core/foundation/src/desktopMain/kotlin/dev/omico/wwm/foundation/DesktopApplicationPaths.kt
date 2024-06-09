/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.foundation

import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.createParentDirectories

private val userHomeDirectory: Path = Path(System.getProperty("user.home"))

private val wwmLocalDirectory: Path =
    DesktopApplicationEnvironments.WWM_LOCAL_DIRECTORY?.let(::Path)
        ?: userHomeDirectory.resolve(".wwm")

val wwmMarkedAchievementsFile: Path = wwmLocalDirectory.resolve("MarkedAchievements.json").createParentDirectories()

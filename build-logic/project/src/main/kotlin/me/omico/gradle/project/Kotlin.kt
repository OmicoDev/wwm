/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.gradle.project

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import java.io.File

internal val Project.kotlinSourcePackage: String
    get() = kotlinSourcePackagePaths.joinToString(".")

internal fun NamedDomainObjectContainer<KotlinSourceSet>.createSourcePackageDirectories(project: Project): Unit =
    configureEach { kotlin.srcDirs.filter(File::exists).forEach(project::createKotlinSourcePackageDirectories) }

private fun Project.createKotlinSourcePackageDirectories(sourceDirectory: File) {
    sourceDirectory.resolve(kotlinSourcePackagePaths.joinToString("/")).mkdirs()
}

private val Project.kotlinSourcePackagePaths: List<String>
    get() {
        var paths = path.removePrefix(":").split('-').drop(1)
        if (paths.first() == "core") paths = paths.drop(1)
        paths = group.toString().split('.') + paths
        return paths
    }

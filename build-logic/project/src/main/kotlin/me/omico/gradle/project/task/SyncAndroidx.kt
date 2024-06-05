/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.gradle.project.task

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.ProjectLayout
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.UntrackedTask
import java.io.File
import javax.inject.Inject

@UntrackedTask(because = "Not worth tracking")
abstract class SyncAndroidx : WwmTask() {
    @get:Inject
    abstract val projectLayout: ProjectLayout

    @get:[InputDirectory PathSensitive(PathSensitivity.ABSOLUTE)]
    abstract val androidxDirectoryProperty: DirectoryProperty

    @get:Input
    abstract val copySpecsProperty: ListProperty<CopySpec>

    override fun execute() {
        val androidxDirectory = androidxDirectoryProperty.asFile.get()
        val copySpecs = copySpecsProperty.get()
        copySpecs
            .map { copySpec ->
                val sourceDirectory = androidxDirectory.resolve(copySpec.sourcePath).resolve("src")
                val targetDirectory = projectLayout.projectDirectory.file(copySpec.targetPath).asFile.resolve("src")
                if (targetDirectory.exists()) targetDirectory.deleteRecursively()
                sourceDirectory to targetDirectory
            }
            .forEach { (sourceDirectory, targetDirectory) ->
                listOf(
                    "androidMain",
                    "commonMain",
                    "desktopMain",
                    "jvmMain",
                ).forEach { sourceSetName ->
                    sourceDirectory.resolve("$sourceSetName/kotlin").takeIf(File::exists)
                        ?.walk()
                        ?.filter { it.extension == "kt" }
                        ?.forEach { sourceFile ->
                            val targetFile = targetDirectory.resolve(sourceFile.relativeTo(sourceDirectory))
                            targetFile.parentFile.mkdirs()
                            sourceFile.copyTo(targetFile)
                        }
                }
            }
    }

    data class CopySpec(
        val sourcePath: String,
        val targetPath: String,
    )
}

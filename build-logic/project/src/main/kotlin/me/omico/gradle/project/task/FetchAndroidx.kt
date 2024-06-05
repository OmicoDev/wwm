/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package me.omico.gradle.project.task

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.internal.ProcessOperations
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.UntrackedTask
import javax.inject.Inject

@UntrackedTask(because = "Not worth tracking")
abstract class FetchAndroidx : WwmTask() {
    @get:Inject
    abstract val processOperations: ProcessOperations

    @get:Input
    abstract val commitIdProperty: Property<String>

    @get:OutputDirectory
    abstract val androidxDirectoryProperty: DirectoryProperty

    override fun execute() {
        val commitId = commitIdProperty.get()
        val androidxDirectory = androidxDirectoryProperty.asFile.get()
        processOperations.exec {
            workingDir = androidxDirectory
            commandLine = listOf("git", "fetch", "--all")
        }
        processOperations.exec {
            workingDir = androidxDirectory
            commandLine = listOf("git", "checkout", commitId)
        }
    }
}

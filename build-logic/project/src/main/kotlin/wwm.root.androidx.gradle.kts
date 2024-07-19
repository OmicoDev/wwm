import me.omico.consensus.api.dsl.consensus
import me.omico.consensus.api.dsl.requireRootProject
import me.omico.gradle.project.task.FetchAndroidx
import me.omico.gradle.project.task.SyncAndroidx

requireRootProject()

val fetchAndroidx: FetchAndroidx by tasks.register<FetchAndroidx>("fetchAndroidx") {
    val androidxDirectory = rootProject.consensus.localProperties.getOrNull<File>("ANDROIDX_DIRECTORY")
    enabled = androidxDirectory != null
    androidxDirectoryProperty.set(androidxDirectory)
    // https://developer.android.com/jetpack/androidx/releases/compose-material3-adaptive#1.0.0-beta04
    commitIdProperty.set("967bd940b24778daf0ac5f9661bc881f4d0487c1")
}

val syncAndroidx: SyncAndroidx by tasks.register<SyncAndroidx>("syncAndroidx") {
    enabled = fetchAndroidx.enabled
    androidxDirectoryProperty.set(fetchAndroidx.androidxDirectoryProperty)
    copySpecsProperty.set(
        listOf(
            SyncAndroidx.CopySpec(
                sourcePath = "compose/material3/adaptive/adaptive",
                targetPath = "wwm/androidx/compose/material3/adaptive",
            ),
            SyncAndroidx.CopySpec(
                sourcePath = "compose/material3/adaptive/adaptive-layout",
                targetPath = "wwm/androidx/compose/material3/adaptive/layout",
            ),
            SyncAndroidx.CopySpec(
                sourcePath = "compose/material3/adaptive/adaptive-navigation",
                targetPath = "wwm/androidx/compose/material3/adaptive/navigation",
            ),
            SyncAndroidx.CopySpec(
                sourcePath = "compose/material3/material3-adaptive-navigation-suite",
                targetPath = "wwm/androidx/compose/material3/adaptive/navigation/suite",
            ),
        ),
    )
}

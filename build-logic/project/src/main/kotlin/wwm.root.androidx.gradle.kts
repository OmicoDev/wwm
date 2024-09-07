import me.omico.consensus.api.dsl.consensus
import me.omico.consensus.api.dsl.requireRootProject
import me.omico.gradle.project.task.FetchAndroidx
import me.omico.gradle.project.task.SyncAndroidx

requireRootProject()

val fetchAndroidx: FetchAndroidx by tasks.register<FetchAndroidx>("fetchAndroidx") {
    val androidxDirectory = rootProject.consensus.localProperties.getOrNull<File>("ANDROIDX_DIRECTORY")
    enabled = androidxDirectory != null
    androidxDirectoryProperty.set(androidxDirectory)
    // https://developer.android.com/jetpack/androidx/releases/compose-material3-adaptive#1.0.0
    commitIdProperty.set("cad2089d1b7edd842b0132ba03a6d2fa4ee7d1a1")
}

val syncAndroidx: SyncAndroidx by tasks.register<SyncAndroidx>("syncAndroidx") {
    enabled = fetchAndroidx.enabled
    androidxDirectoryProperty.set(fetchAndroidx.androidxDirectoryProperty)
    copySpecsProperty.set(
        listOf(
            SyncAndroidx.CopySpec(
                sourcePath = "compose/material3/material3-adaptive-navigation-suite",
                targetPath = "wwm/androidx/compose/material3/adaptive/navigation/suite",
            ),
        ),
    )
}

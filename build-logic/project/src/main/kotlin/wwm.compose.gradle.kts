import me.omico.gradle.project.configureCommonComposeMultiplatform
import me.omico.gradm.Versions

plugins {
    kotlin("plugin.compose")
    id("org.jetbrains.compose")
}

composeCompiler {
    metricsDestination = file("build/composeMetrics")
    reportsDestination = file("build/composeReports")
}

configureCommonComposeMultiplatform(
    composeMultiplatformVersion = Versions.compose.multiplatform,
)

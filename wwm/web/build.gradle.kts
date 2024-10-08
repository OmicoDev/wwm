import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("wwm.compose.multiplatform")
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "wwm.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":wwm-core-application"))
            }
        }
    }
}

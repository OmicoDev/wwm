plugins {
    id("wwm.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":wwm-androidx-compose-material3-adaptive-navigation-suite"))
                api(project(":wwm-core-foundation"))
            }
            dependencies {
                api(circuit.foundation)
                api(compose.components.resources)
                api(compose.material3)
                api(delusion.compose)
                api(jetbrains.androidx.window.core)
                api(jetbrains.compose.material3.adaptive)
                api(jetbrains.compose.material3.adaptive.layout)
                api(jetbrains.compose.material3.adaptive.navigation)
            }
        }

        jvmCommonMain {
            dependencies {
                api(compose.preview)
            }
        }
    }
}

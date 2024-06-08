plugins {
    id("wwm.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":wwm-androidx-compose-material3-adaptive"))
                api(project(":wwm-androidx-compose-material3-adaptive-layout"))
                api(project(":wwm-androidx-compose-material3-adaptive-navigation"))
                api(project(":wwm-androidx-compose-material3-adaptive-navigation-suite"))
                api(project(":wwm-androidx-window-core"))
                api(project(":wwm-core-foundation"))
            }
            dependencies {
                api(circuit.foundation)
                api(compose.components.resources)
                api(compose.material3)
                api(delusion.compose)
            }
        }

        jvmMain {
            dependencies {
                api(compose.preview)
            }
        }
    }
}

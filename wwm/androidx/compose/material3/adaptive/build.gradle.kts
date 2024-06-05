plugins {
    id("wwm.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":wwm-androidx-window-core"))
            }
            dependencies {
                implementation(compose.ui)
            }
        }
    }
}

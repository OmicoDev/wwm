plugins {
    id("wwm.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":wwm-androidx-compose-material3-adaptive"))
                implementation(project(":wwm-androidx-compose-material3-adaptive-layout"))
            }
            dependencies {
                implementation(compose.ui)
            }
        }
    }
}

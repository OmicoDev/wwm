plugins {
    id("wwm.compose.multiplatform")
}

kotlin {
    sourceSets {
        all {
            languageSettings {
                enableLanguageFeature("ContextReceivers")
            }
        }
        commonMain {
            dependencies {
                implementation(project(":wwm-androidx-compose-material3-adaptive"))
                implementation(project(":wwm-androidx-window-core"))
            }
            dependencies {
                implementation(compose.animation)
            }
        }
    }
}

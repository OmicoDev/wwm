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
                implementation(project(":wwm-core-ui"))
            }
        }
    }
}

plugins {
    id("wwm.compose.multiplatform.resources")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":wwm-core-resources-model"))
                api(project(":wwm-core-resources-runtime"))
            }
            dependencies {
                implementation(project(":wwm-core-foundation"))
            }
            dependencies {
                implementation(kotlinx.serialization.json)
            }
        }
    }
}

plugins {
    id("wwm.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":wwm-core-data"))
                api(project(":wwm-core-resources"))
                api(project(":wwm-core-ui-foundation"))
            }
        }
    }
}

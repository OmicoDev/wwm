plugins {
    id("wwm.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":wwm-core-feature-achievements"))
                api(project(":wwm-core-ui"))
            }
        }
    }
}

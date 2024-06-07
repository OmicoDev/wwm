plugins {
    id("wwm.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":wwm-core-resources"))
                implementation(project(":wwm-core-ui"))
            }
        }
    }
}

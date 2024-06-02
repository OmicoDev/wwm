plugins {
    id("wwm.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":wwm-core-resources-model"))
            }
        }
    }
}

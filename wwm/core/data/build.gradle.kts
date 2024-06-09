plugins {
    id("wwm.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":wwm-core-foundation"))
                implementation(project(":wwm-core-resources"))
            }
            dependencies {
                implementation(kotlinx.serialization.json)
            }
        }
    }
}

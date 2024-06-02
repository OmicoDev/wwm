plugins {
    id("wwm.kotlin.multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlinx.serialization.core)
            }
        }
    }
}

plugins {
    id("wwm.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlinx.coroutines.core)
            }
        }
    }
}

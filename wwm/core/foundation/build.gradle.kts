plugins {
    id("wwm.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(kotlinx.coroutines.core)
            }
        }
    }
}

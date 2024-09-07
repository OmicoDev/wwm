plugins {
    id("wwm.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(jetbrains.androidx.window.core)
                implementation(jetbrains.compose.material3.adaptive)
            }
            dependencies {
                implementation(compose.material3)
            }
        }
    }
}

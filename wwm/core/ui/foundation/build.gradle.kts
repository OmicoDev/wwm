plugins {
    id("wwm.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":wwm-core-foundation"))
            }
            dependencies {
                api(circuit.foundation)
                api(compose.material3)
            }
        }

        jvmMain {
            dependencies {
                api(compose.preview)
            }
        }
    }
}

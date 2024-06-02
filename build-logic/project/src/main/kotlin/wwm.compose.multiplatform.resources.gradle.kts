plugins {
    id("wwm.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.components.resources)
                implementation(compose.runtime)
            }
        }
    }
}

compose {
    resources {
        generateResClass = always
    }
}

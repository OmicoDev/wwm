plugins {
    id("wwm.desktop.application")
}

compose {
    desktop {
        application {
            mainClass = "dev.omico.wwm.desktop.WwmDesktop"
        }
    }
}

dependencies {
    implementation(project(":wwm-core-application"))
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(kotlinx.coroutines.swing)
}

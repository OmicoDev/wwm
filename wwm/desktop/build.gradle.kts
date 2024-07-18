import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("wwm.desktop.application")
}

compose {
    desktop {
        application {
            mainClass = "dev.omico.wwm.desktop.WwmDesktop"
            nativeDistributions {
                packageVersion = "1.0.0"
                targetFormats(TargetFormat.AppImage)
                windows {
                    iconFile = file("wwm-windows.ico")
                }
                macOS {
                    targetFormats(TargetFormat.Dmg)
                }
            }
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

import me.omico.gradle.project.kotlinSourcePackage

plugins {
    id("wwm.kotlin.multiplatform")
    id("wwm.compose")
}

compose {
    resources {
        packageOfResClass = "$kotlinSourcePackage.generated"
        generateResClass = never
    }
}

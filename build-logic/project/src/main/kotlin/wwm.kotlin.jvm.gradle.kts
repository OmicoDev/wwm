import me.omico.gradle.project.PROJECT_JAVA_VERSION
import me.omico.gradle.project.createSourcePackageDirectories

plugins {
    kotlin("jvm")
}

kotlin {
    jvmToolchain(PROJECT_JAVA_VERSION)

    sourceSets {
        createSourcePackageDirectories(project)
    }
}

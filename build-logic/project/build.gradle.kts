plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(com.diffplug.spotless)
    implementation(consensusGradlePlugins)
    implementation(gradmGeneratedJar)
    implementation(kotlinGradlePlugin)
    implementation(org.jetbrains.compose)
    implementation(org.jetbrains.kotlin.plugin.compose)
}

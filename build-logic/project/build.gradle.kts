plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(com.diffplug.spotless)
    implementation(gradmGeneratedJar)
    implementation(kotlinGradlePlugin)
    implementation(me.omico.consensus.api)
    implementation(me.omico.consensus.git)
    implementation(me.omico.consensus.spotless)
    implementation(org.jetbrains.compose)
    implementation(org.jetbrains.kotlin.plugin.compose)
}

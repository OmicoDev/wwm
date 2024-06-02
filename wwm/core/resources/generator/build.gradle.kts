plugins {
    id("wwm.cli.application")
}

application {
    mainClass = "dev.omico.wwm.resources.generator.WwmResourceGenerator"
}

dependencies {
    implementation(project(":wwm-core-resources-runtime"))
}

dependencies {
    implementation(kotlinx.serialization.json)
}

val gameDataDirectory = consensus.sensitiveProperty<String?>("wwm.game.data.directory", null)

tasks.run<JavaExec> {
    enabled = gameDataDirectory != null
    args(
        gameDataDirectory ?: return@run,
        project(":wwm-core-resources").projectDir.resolve("src/commonMain/composeResources").absolutePath,
    )
}

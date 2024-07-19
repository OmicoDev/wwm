import me.omico.consensus.dsl.requireRootProject

plugins {
    id("wwm.gradm")
    id("wwm.root.androidx")
    id("wwm.root.git")
    id("wwm.root.spotless")
}

requireRootProject()

consensus {
    allprojects {
        group = gradleProperty("project.group")
        version = gradleProperty("project.version")
    }
}

val wrapper: Wrapper by tasks.named<Wrapper>("wrapper") {
    gradleVersion = versions.gradle
    distributionType = Wrapper.DistributionType.BIN
}

val updateYarnLock by tasks.register<Delete>("updateYarnLock") {
    notCompatibleWithConfigurationCache("Updating yarn.lock is not compatible with configuration cache.")
    doFirst { delete("build/js/yarn.lock", "kotlin-js-store/yarn.lock") }
    finalizedBy(":wwm-web:assemble")
}

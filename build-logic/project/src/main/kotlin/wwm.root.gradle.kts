plugins {
    id("me.omico.consensus.root")
    id("wwm.gradm")
    id("wwm.root.androidx")
    id("wwm.root.git")
    id("wwm.root.spotless")
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = versions.gradle
    distributionType = Wrapper.DistributionType.BIN
}

val updateYarnLock by tasks.register<Delete>("updateYarnLock") {
    notCompatibleWithConfigurationCache("Updating yarn.lock is not compatible with configuration cache.")
    doFirst { delete("build/js/yarn.lock", "kotlin-js-store/yarn.lock") }
    finalizedBy(":wwm-web:assemble")
}

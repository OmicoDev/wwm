import me.omico.gradle.initialization.includeAllSubprojectModules
import me.omico.gradm.addDeclaredRepositories

addDeclaredRepositories()

plugins {
    id("wwm.develocity")
    id("wwm.gradm")
}

includeBuild("build-logic/project")

includeAllSubprojectModules("wwm")

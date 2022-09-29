pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Monkey Business"
include(":androidApp")
include(":core")
include(":network")
include(":mvi:feature")
include(":mvi:feature-logging")
include(":components:home:shared")
include(":finance:models")

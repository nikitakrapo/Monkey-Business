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
include(":mvi:feature")
include(":mvi:feature-logging")
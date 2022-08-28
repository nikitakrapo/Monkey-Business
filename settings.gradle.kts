pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Monkey_Forecast"
include(":androidApp")
include(":core")
include(":mvi:feature")
include(":mvi:feature-logging")
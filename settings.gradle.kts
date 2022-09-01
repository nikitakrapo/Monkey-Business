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
include(":home")
include(":finance:models")

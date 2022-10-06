enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

rootProject.name = "Monkey Business"
include(":androidApp")
include(":core")
include(":network")
include(":mvi:feature")
include(":mvi:feature-logging")
include(":components:home")
include(":finance:models")

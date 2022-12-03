enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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

rootProject.name = "MonkeyBusiness"

include(":androidApp")
include(":core")
include(":network")
include(":analytics")
include(":mvi:feature")
include(":mvi:feature-logging")
include(":finance:models")
include(":design")

include(":components:home")

include(":features:navigation:core")
include(":features:navigation:test")

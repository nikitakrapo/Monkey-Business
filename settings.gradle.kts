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
include(":analytics")
include(":design")

include(":components:home")

include(":features:navigation:core")
include(":features:navigation:test")
include(":features:mvi:feature")
include(":features:mvi:feature-logging")
include(":features:network")
include(":features:finance:models")

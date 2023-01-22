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
include(":design")

include(":components:authentication")
include(":components:home")
include(":components:finances")
include(":components:profile")

include(":features:kmmutils")
include(":features:navigation:core")
include(":features:navigation:test")
include(":features:mvi:feature")
include(":features:mvi:feature-logging")
include(":features:mvi:decompose-ext")
include(":features:network")
include(":features:analytics")
include(":features:account:core")
include(":features:account:test")
include(":features:finance:models")
include(":features:finance:data")

include(":macrobenchmark")

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

include(":components:analytics")
include(":components:authentication")
include(":components:home")
include(":components:finances")
include(":components:profile")
include(":components:settings")

include(":features:design-compose")
include(":features:android-resources")
include(":features:kmm-utils")
include(":features:navigation:core")
include(":features:navigation:test")
include(":features:mvi:feature")
include(":features:mvi:feature-logging")
include(":features:mvi:decompose-ext")
include(":features:network")
include(":features:database")
include(":features:datastore")
include(":features:analytics")
include(":features:account-manager:core")
include(":features:account-manager:test")
include(":features:finance:models")
include(":features:finance:data")

include(":macrobenchmark")

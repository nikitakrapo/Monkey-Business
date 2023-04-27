import com.nikitakrapo.configuration.android.applyCompose
import com.nikitakrapo.configuration.multiplatform.multiplatformMobileTargets
import com.nikitakrapo.configuration.multiplatform.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("kotlin-parcelize")
}

version = "1.0"

setupMultiplatformModule(
    targets = ::multiplatformMobileTargets,
    withUtils = true,
)

applyCompose()

kotlin {
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            isStatic = true
            baseName = "core"
            export(projects.features.navigation.core)
            export(projects.features.analytics)
            export(projects.features.accountManager.core)
            export(projects.components.authentication)
            export(projects.components.home)
            export(projects.components.profile)
            transitiveExport = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.features.navigation.core)
                api(projects.features.analytics)
                api(projects.features.accountManager.core)
                api(projects.components.authentication)
                api(projects.components.home)
                api(projects.components.profile)
                implementation(projects.features.mvi.feature)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(projects.design)
                implementation(libs.decompose.jetpack)
            }
        }
    }
}

import com.nikitakrapo.configuration.applyCompose
import com.nikitakrapo.configuration.iosCompat
import com.nikitakrapo.configuration.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("kotlin-parcelize")
}

version = "1.0"

setupMultiplatformModule {
    android()
    iosCompat()
}

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
            export(projects.features.account.core)
            export(projects.features.kmmutils)
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
                api(projects.features.account.core)
                api(projects.features.kmmutils)
                implementation(projects.features.mvi.feature)
                //FIXME: remove
                implementation(projects.features.finance.data)
                api(projects.components.authentication)
                api(projects.components.home)
                api(projects.components.profile)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(projects.features.mvi.featureLogging)
                implementation(projects.features.navigation.test)
                implementation(projects.features.account.test)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.turbine)
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

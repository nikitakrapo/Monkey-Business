import com.nikitakrapo.configuration.multiplatform.setupMobileMultiplatformModule

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("kotlin-parcelize")
}

version = "1.0"

setupMobileMultiplatformModule(
    androidNamespace = "com.nikitakrapo.monkeybusiness.core",
    androidConfiguration = {
        useCompose()
    }
)

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
                implementation(projects.features.kmmUtils)
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

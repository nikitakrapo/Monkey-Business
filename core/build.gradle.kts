import com.nikitakrapo.modules_setup.applyCompose
import com.nikitakrapo.modules_setup.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

setupMultiplatformModule()

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
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.features.navigation.core)
                api(projects.features.analytics)
                implementation(projects.features.mvi.feature)
                implementation(projects.components.home)
            }
        }
        val commonTest by getting {
            dependencies {
                api(projects.features.navigation.test)
                implementation(projects.features.mvi.featureLogging)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.turbine)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(projects.design)
            }
        }
    }
}

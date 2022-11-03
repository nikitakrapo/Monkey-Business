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
        name = "Home"
        summary = "Homepage of the application"
        ios.deploymentTarget = "14.1"
        framework {
            isStatic = true
            baseName = "home"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.mvi.feature)
                implementation(projects.finance.models)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(projects.mvi.featureLogging)
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

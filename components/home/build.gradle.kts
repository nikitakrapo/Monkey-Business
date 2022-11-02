import modulesSetup.iosCompat
import modulesSetup.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

setupMultiplatformModule {
    android()
    jvm()
    iosCompat()
}

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
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
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

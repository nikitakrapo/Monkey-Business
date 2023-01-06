import com.nikitakrapo.configuration.applyCompose
import com.nikitakrapo.configuration.iosCompat
import com.nikitakrapo.configuration.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
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
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.components.finances)
                api(projects.components.profile)
                api(projects.features.finance.models)
                api(projects.features.analytics)
                implementation(projects.features.mvi.feature)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(projects.features.mvi.featureLogging)
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

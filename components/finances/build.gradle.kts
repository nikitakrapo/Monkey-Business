import com.nikitakrapo.configuration.android.applyCompose
import com.nikitakrapo.configuration.multiplatform.multiplatformMobileTargets
import com.nikitakrapo.configuration.multiplatform.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

version = "1.0"

setupMultiplatformModule(targets = ::multiplatformMobileTargets)

applyCompose()

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.features.finance.data)
                api(projects.features.finance.models)
                api(projects.features.navigation.core)
                api(projects.features.analytics)
                implementation(projects.features.mvi.feature)
                implementation(libs.kotlinx.datetime)
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
            }
        }
    }
}

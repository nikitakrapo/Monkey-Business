import com.nikitakrapo.configuration.android.applyCompose
import com.nikitakrapo.configuration.multiplatform.multiplatformMobileTargets
import com.nikitakrapo.configuration.multiplatform.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-parcelize")
}

version = "1.0"

setupMultiplatformModule(targets = ::multiplatformMobileTargets)

applyCompose()

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.features.account.core)
                api(projects.features.navigation.core)
                implementation(projects.features.mvi.feature)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(projects.design)
                implementation(libs.coil.compose)
                implementation(libs.decompose.jetpack)
            }
        }
    }
}

import com.nikitakrapo.configuration.multiplatform.multiplatformMobileTargets
import com.nikitakrapo.configuration.multiplatform.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

version = "1.0"

setupMultiplatformModule(targets = ::multiplatformMobileTargets)

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.features.mvi.feature)
                api(libs.decompose)
                api(libs.kotlinx.coroutines)
            }
        }
    }
}

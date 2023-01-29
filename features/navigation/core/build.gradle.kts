import com.nikitakrapo.configuration.multiplatform.multiplatformMobileTargets
import com.nikitakrapo.configuration.multiplatform.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

setupMultiplatformModule(targets = ::multiplatformMobileTargets)

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlinx.coroutines)
                api(libs.decompose)
                api(projects.features.mvi.feature)
                api(projects.features.mvi.decomposeExt)
            }
        }
    }
}

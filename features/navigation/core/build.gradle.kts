import com.nikitakrapo.configuration.multiplatform.setupMobileMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

setupMobileMultiplatformModule(androidNamespace = "com.nikitakrapo.monkeybusiness.navigation")

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

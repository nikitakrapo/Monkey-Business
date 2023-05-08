import com.nikitakrapo.configuration.multiplatform.setupMobileMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

version = "1.0"

setupMobileMultiplatformModule(androidNamespace = "com.nikitakrapo.monkeybusiness.decompose.extensions")

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

import com.nikitakrapo.configuration.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

version = "1.0"

setupMultiplatformModule()

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

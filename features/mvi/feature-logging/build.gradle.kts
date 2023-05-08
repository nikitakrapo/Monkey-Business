import com.nikitakrapo.configuration.multiplatform.setupMobileMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

version = "1.0"

setupMobileMultiplatformModule(androidNamespace = "com.nikitakrapo.mvi.feature.logging")

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(libs.kotlinx.coroutines)
                api(projects.features.mvi.feature)
            }
        }
    }
}

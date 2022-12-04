import com.nikitakrapo.modules_setup.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.7.20"
}

setupMultiplatformModule()

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                // TODO: maybe it's not good to use serialization lib as api
                api(libs.kotlinx.serialization.json)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.firebase.analytics)
            }
        }
    }
}

import com.nikitakrapo.configuration.multiplatform.multiplatformMobileTargets
import com.nikitakrapo.configuration.multiplatform.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version libs.versions.kotlin
}

version = "1.0"

setupMultiplatformModule(targets = ::multiplatformMobileTargets)

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.serialization.json)
                api(libs.kotlinx.datetime)
            }
        }
        val androidMain by getting
    }
}

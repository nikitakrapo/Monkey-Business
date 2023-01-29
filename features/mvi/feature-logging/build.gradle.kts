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
                implementation(kotlin("stdlib"))
                implementation(libs.kotlinx.coroutines)
                api(projects.features.mvi.feature)
            }
        }
    }
}

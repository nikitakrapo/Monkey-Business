import com.nikitakrapo.configuration.multiplatform.multiplatformMobileTargets
import com.nikitakrapo.configuration.multiplatform.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

setupMultiplatformModule(
    withUtils = true,
    targets = ::multiplatformMobileTargets,
)

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.features.accountManager.core)
                implementation(libs.kotlinx.coroutines)
            }
        }
    }
}

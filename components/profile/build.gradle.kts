import com.nikitakrapo.configuration.multiplatform.setupMobileMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-parcelize")
}

version = "1.0"

setupMobileMultiplatformModule(
    androidNamespace = "com.nikitakrapo.monkeybusiness.profile",
    androidConfiguration = {
        useCompose()
    }
)

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.features.accountManager.core)
                api(projects.features.navigation.core)
                implementation(projects.features.mvi.feature)
                implementation(projects.features.androidResources)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(projects.features.designCompose)
                implementation(libs.coil.compose)
            }
        }
    }
}

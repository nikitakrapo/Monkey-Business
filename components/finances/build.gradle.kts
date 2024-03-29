import com.nikitakrapo.configuration.multiplatform.setupMobileMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

version = "1.0"

setupMobileMultiplatformModule(
    androidNamespace = "com.nikitakrapo.monkeybusiness.finances",
    androidConfiguration = {
        useCompose()
    }
)

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.features.finance.data)
                api(projects.features.finance.models)
                api(projects.features.navigation.core)
                api(projects.features.analytics)
                api(libs.mvikotlin.core)
                api(libs.mvikotlin.main)
                implementation(libs.mvikotlin.coroutines)
                implementation(projects.features.kmmUtils)
                implementation(projects.features.androidResources)
                implementation(libs.kotlinx.datetime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(projects.features.mvi.featureLogging)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.turbine)
                implementation(libs.compose.shimmer)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(projects.features.designCompose)
                implementation(libs.compose.shimmer)
            }
        }
    }
}

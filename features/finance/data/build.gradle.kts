import com.nikitakrapo.configuration.multiplatform.setupMobileMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
    kotlin("plugin.serialization") version libs.versions.kotlin
}

version = "1.0"

setupMobileMultiplatformModule(androidNamespace = "com.nikitakrapo.monkeybusiness.finance.data")

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.features.finance.models)
                api(libs.kotlinx.coroutines)
                api(projects.features.network)
                implementation(projects.features.kmmUtils)
                implementation(libs.kotlinx.serialization.json)
                implementation(projects.features.database)
                implementation(libs.sqldelight.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.turbine)
                implementation(libs.kotlinx.coroutines.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.sqldelight.android)
            }
        }
        val androidInstrumentedTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation(libs.androidx.junit)
                implementation(libs.androidx.junit.ktx)
                implementation(libs.androidx.espresso)
            }
        }
    }
}

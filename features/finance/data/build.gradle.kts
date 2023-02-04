import com.nikitakrapo.configuration.multiplatform.multiplatformMobileTargets
import com.nikitakrapo.configuration.multiplatform.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
    kotlin("plugin.serialization") version libs.versions.kotlin
}

version = "1.0"

setupMultiplatformModule(targets = ::multiplatformMobileTargets)

sqldelight {
    database("TransactionsDatabase") {
        packageName = "finance.transactions"
    }
}

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
                api(projects.features.kmmutils)
                api(libs.kotlinx.coroutines)
                api(projects.features.network)
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
        val androidAndroidTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation(libs.androidx.junit)
                implementation(libs.androidx.junit.ktx)
                implementation(libs.androidx.espresso)
            }
        }
    }
}
import com.nikitakrapo.configuration.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

setupMultiplatformModule()

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlinx.coroutines)
                api(libs.decompose)
            }
        }
        val commonTest by getting

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.activity.ktx)
            }
        }
    }
}

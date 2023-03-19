import com.nikitakrapo.configuration.multiplatform.multiplatformMobileTargets
import com.nikitakrapo.configuration.multiplatform.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
    kotlin("plugin.serialization") version libs.versions.kotlin
}

version = "1.0"

setupMultiplatformModule(
    targets = ::multiplatformMobileTargets,
    withUtils = true,
)

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.sqldelight.runtime)
                api(libs.kotlinx.coroutines)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.sqldelight.android)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.sqldelight.ios)
            }
        }
    }
}

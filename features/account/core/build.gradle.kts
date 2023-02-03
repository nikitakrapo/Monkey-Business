import com.nikitakrapo.configuration.multiplatform.multiplatformMobileTargets
import com.nikitakrapo.configuration.multiplatform.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("native.cocoapods")
}

setupMultiplatformModule(
    withUtils = true,
    targets = ::multiplatformMobileTargets
)

kotlin {
    cocoapods {
        name = "AccountCore"
        version = "1.0"

        ios.deploymentTarget = "14.1"

        pod("FirebaseAuth") {
            version = "~> 10.3.0"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.features.analytics)
                implementation(libs.kotlinx.coroutines)
                implementation(projects.features.network)
                implementation(libs.essenty.parcelable)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.firebase.auth)
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
        }
    }
}

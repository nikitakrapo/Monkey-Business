import com.nikitakrapo.configuration.multiplatform.multiplatformMobileTargets
import com.nikitakrapo.configuration.multiplatform.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version libs.versions.kotlin
    kotlin("native.cocoapods")
}

setupMultiplatformModule(targets = ::multiplatformMobileTargets)

kotlin {
    cocoapods {
        version = "1.0"

        ios.deploymentTarget = "14.1"

        pod(
            name = "FirebaseAnalytics",
            path = File("$rootDir/ios-frameworks/FirebaseAnalytics"),
            version = "10.6.0",
        )

        pod(
            name = "FirebaseCrashlytics",
            path = File("$rootDir/ios-frameworks/FirebaseCrashlytics"),
            version = "10.6.0",
        )
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // TODO: maybe it's not good to use serialization lib as api
                api(libs.kotlinx.serialization.json)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.firebase.analytics)
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

import com.nikitakrapo.configuration.multiplatform.setupMobileMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version libs.versions.kotlin
    kotlin("native.cocoapods")
}

setupMobileMultiplatformModule(androidNamespace = "com.nikitakrapo.monkeybusiness.analytics")

kotlin {
    cocoapods {
        version = "1.0"

        ios.deploymentTarget = "14.1"

        pod("FirebaseAnalytics") {
            version = "~> 10.3.0"
        }
        pod("FirebaseCrashlytics") {
            version = "~> 10.3.0"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
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

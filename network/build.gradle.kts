import com.nikitakrapo.modules_setup.iosCompat
import com.nikitakrapo.modules_setup.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

setupMultiplatformModule()

kotlin {
    cocoapods {
        name = "MonkeyNetwork"
        summary = "Networking module"
        ios.deploymentTarget = "14.1"
        framework {
            isStatic = true
            baseName = "network"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.ktor.core)
                api(libs.ktor.logging)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.okhttp)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ktor.darwin)
            }
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}
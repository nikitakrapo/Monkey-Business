import com.nikitakrapo.configuration.multiplatform.setupMobileMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

version = "1.0"

setupMobileMultiplatformModule(androidNamespace = "com.nikitakrapo.monkeybusiness.network")


kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.ktor.core)
                api(libs.ktor.auth)
                api(libs.ktor.logging)
                implementation(libs.ktor.contentnegotiation)
                implementation(libs.ktor.json)
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

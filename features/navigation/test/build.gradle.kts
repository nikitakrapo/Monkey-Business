import com.nikitakrapo.configuration.multiplatform.multiplatformMobileTargets
import com.nikitakrapo.configuration.multiplatform.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

setupMultiplatformModule(targets = ::multiplatformMobileTargets)

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlinx.coroutines)
                api(libs.decompose)
            }
        }
        val commonTest by getting

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val nonAndroid by creating {
            dependsOn(commonMain)
            iosMain.dependsOn(this)
        }
    }
}

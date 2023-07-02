import com.nikitakrapo.configuration.multiplatform.setupMobileMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

setupMobileMultiplatformModule(androidNamespace = "com.nikitakrapo.monkeybusiness.datastore")

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.settings)
                api(projects.features.kmmUtils)
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

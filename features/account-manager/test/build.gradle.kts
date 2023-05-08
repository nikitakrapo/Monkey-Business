import com.nikitakrapo.configuration.multiplatform.setupMobileMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

setupMobileMultiplatformModule(androidNamespace = "com.nikitakrapo.monkeybusiness.account.test")

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines)
                implementation(projects.features.accountManager.core)
                implementation(projects.features.kmmUtils)
            }
        }
    }
}

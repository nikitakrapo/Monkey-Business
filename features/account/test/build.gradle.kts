import com.nikitakrapo.configuration.iosCompat
import com.nikitakrapo.configuration.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

setupMultiplatformModule(withUtils = true) {
    android()
    iosCompat()
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.features.account.core)
                implementation(libs.kotlinx.coroutines)
            }
        }
    }
}

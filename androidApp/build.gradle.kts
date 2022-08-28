import com.nikitakrapo.build.ApplicationConfig
import com.nikitakrapo.build.Versions

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = Versions.compileSdk
    defaultConfig {
        applicationId = ApplicationConfig.applicationId
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = ApplicationConfig.versionCode
        versionName = ApplicationConfig.versionName
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
}
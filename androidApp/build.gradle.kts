plugins {
    id("convention.android-mobile-app")
    id("convention.compose")
}

android {
    defaultConfig {
        applicationId = "com.nikitakrapo.monkeybusiness"
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.coroutines)
}
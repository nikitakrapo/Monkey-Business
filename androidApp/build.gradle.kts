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
    implementation(projects.core)
    implementation(projects.design)
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.androidx.activity.compose)
    implementation(libs.accompanist.systemuicontroller)
}
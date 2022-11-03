import com.nikitakrapo.modules_setup.applyCompose
import com.nikitakrapo.modules_setup.setupAndroidApp

plugins {
    id("com.android.application")
    kotlin("android")
}

setupAndroidApp(
    applicationId = "com.nikitakrapo.monkeybusiness",
    versionCode = 1,
    versionName = "1.0",
)

applyCompose()

dependencies {
    implementation(projects.core)
    implementation(projects.design)
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.androidx.activity.compose)
    implementation(libs.accompanist.systemuicontroller)
}
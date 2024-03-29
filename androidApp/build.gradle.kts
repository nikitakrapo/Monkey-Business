import com.nikitakrapo.configuration.android.AndroidApplicationConfig
import com.nikitakrapo.configuration.android.androidModuleConfig
import com.nikitakrapo.configuration.android.setupAndroidApp

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

val appId = "com.nikitakrapo.monkeybusiness"
setupAndroidApp(
    appConfig = AndroidApplicationConfig(
        applicationId = appId,
        versionCode = 1,
        versionName = "1.0",
    ),
    moduleConfig = androidModuleConfig(appId)
) {
    val supportedLocales = listOf("en", "ru")
    setResourceConfigs(supportedLocales)

    useCompose()
    configureBenchmarkBuildType()
}

dependencies {
    implementation(projects.core)
    implementation(projects.features.designCompose)
    implementation(projects.features.accountManager.core)
    implementation(projects.features.analytics)
    implementation(projects.features.kmmUtils)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.androidx.appcompat)
    implementation(libs.decompose)
    implementation(libs.profile.installer)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.androidx.activity.compose)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
}

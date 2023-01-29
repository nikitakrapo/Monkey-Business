import com.nikitakrapo.configuration.android.applyCompose
import com.nikitakrapo.configuration.android.setupAndroidLibrary

plugins {
    id("com.android.library")
    kotlin("android")
}

setupAndroidLibrary()

applyCompose()

dependencies {
    api(libs.compose.ui)
    api(libs.compose.material)
    api(libs.compose.material3)
    api(libs.compose.foundation)
    api(libs.compose.ui.tooling)
    api(libs.compose.ui.tooling.preview)
    api(libs.constraintlayout.compose)
}

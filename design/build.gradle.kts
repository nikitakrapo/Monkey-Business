plugins {
    id("convention.android-mobile-lib")
    id("convention.compose")
}

dependencies {
    api(libs.compose.ui)
    api(libs.compose.material3)
    api(libs.compose.foundation)
    api(libs.compose.ui.tooling)
    api(libs.compose.ui.tooling.preview)
}
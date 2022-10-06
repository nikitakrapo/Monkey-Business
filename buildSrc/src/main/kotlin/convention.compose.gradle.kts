import com.android.build.gradle.BaseExtension

/**
 * Convention for modules using jetpack compose
 */
configure<BaseExtension> {
    buildFeatures.compose = true
    composeOptions {
        withVersionCatalog { libs ->
            kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerCompose.get()
        }
    }
}

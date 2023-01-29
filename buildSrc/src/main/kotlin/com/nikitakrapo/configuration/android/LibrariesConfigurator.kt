package com.nikitakrapo.configuration.android

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import withVersionCatalog

/*
TODO: Move out as smth like project-builder:

androidLibrary {
  usesDagger = true
  usesCompose = true
}

or smth like that
*/
fun Project.applyCompose() {
    configure<BaseExtension> {
        buildFeatures.compose = true
        composeOptions {
            withVersionCatalog { libs ->
                kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerCompose.get()
            }
        }
    }
}

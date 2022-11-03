package com.nikitakrapo.modules_setup

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import withVersionCatalog

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
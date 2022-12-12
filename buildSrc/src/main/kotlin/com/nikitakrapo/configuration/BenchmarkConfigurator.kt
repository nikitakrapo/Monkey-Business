package com.nikitakrapo.configuration

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.createBenchmarkBuildType() {
    configure<BaseExtension> {
        buildTypes {
            create("benchmark") {
                signingConfig = signingConfigs.getByName("debug")
                matchingFallbacks += listOf("release")
                isDebuggable = false
            }
        }
    }
}
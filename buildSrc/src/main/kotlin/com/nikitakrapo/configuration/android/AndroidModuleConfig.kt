package com.nikitakrapo.configuration.android

import com.nikitakrapo.configuration.libs
import org.gradle.api.Project

data class AndroidModuleConfig(
    val namespace: String,
    val minSdk: Int,
    val targetSdk: Int,
    val compileSdk: Int,
)

fun Project.androidModuleConfig(
    namespace: String,
) = AndroidModuleConfig(
    namespace = namespace,
    minSdk = libs.versions.minSdk.get().toInt(),
    targetSdk = libs.versions.targetSdk.get().toInt(),
    compileSdk = libs.versions.compileSdk.get().toInt(),
)

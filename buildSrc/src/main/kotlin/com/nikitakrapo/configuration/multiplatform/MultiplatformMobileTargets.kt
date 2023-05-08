package com.nikitakrapo.configuration.multiplatform

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun KotlinMultiplatformExtension.iosCompat() {
    iosArm64()
    iosX64()
    iosSimulatorArm64()
}

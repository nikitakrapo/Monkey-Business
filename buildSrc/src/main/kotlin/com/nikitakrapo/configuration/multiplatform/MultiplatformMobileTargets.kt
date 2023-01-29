package com.nikitakrapo.configuration.multiplatform

import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun multiplatformMobileTargets(extension: KotlinMultiplatformExtension) = with(extension) {
    android()
    iosCompat()
}

fun KotlinMultiplatformExtension.iosCompat() {
    iosArm64()
    iosX64()
    iosSimulatorArm64()
}

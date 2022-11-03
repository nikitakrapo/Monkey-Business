package com.nikitakrapo.modules_setup

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun KotlinMultiplatformExtension.iosCompat(
    arm64: Boolean = true,
    x64: Boolean = true,
    simulatorArm64: Boolean = true,
) {
    if (arm64) iosArm64()
    if (x64) iosX64()
    if (simulatorArm64) iosSimulatorArm64()
}

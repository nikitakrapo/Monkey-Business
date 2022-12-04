package com.nikitakrapo.modules_setup

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun multiplatformDefaultTargets(extension: KotlinMultiplatformExtension) = with(extension) {
    android()
    jvm()
    iosCompat()
}

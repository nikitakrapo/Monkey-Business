package com.nikitakrapo.configuration

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun multiplatformDefaultTargets(extension: KotlinMultiplatformExtension) = with(extension) {
    android()
    jvm()
    iosCompat()
}

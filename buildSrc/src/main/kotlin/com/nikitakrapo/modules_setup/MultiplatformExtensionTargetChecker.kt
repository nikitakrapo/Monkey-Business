package com.nikitakrapo.modules_setup

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

internal fun KotlinMultiplatformExtension.isAndroidTargetEnabled() = targets.any {
    it.platformType == KotlinPlatformType.androidJvm
}

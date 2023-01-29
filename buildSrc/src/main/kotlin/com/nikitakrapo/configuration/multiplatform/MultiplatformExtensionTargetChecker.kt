package com.nikitakrapo.configuration.multiplatform

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

internal fun KotlinMultiplatformExtension.isAndroidTargetEnabled() = targets.any {
    it.platformType == KotlinPlatformType.androidJvm
}

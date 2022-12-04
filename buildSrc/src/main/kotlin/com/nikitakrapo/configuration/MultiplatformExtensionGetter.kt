package com.nikitakrapo.configuration

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

internal val Project.multiplatformExtension get() = kotlinExtension as KotlinMultiplatformExtension

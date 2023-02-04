package com.nikitakrapo.sourcesets

import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

data class SourceSetBundle(
    val main: KotlinSourceSet,
    val test: KotlinSourceSet,
)

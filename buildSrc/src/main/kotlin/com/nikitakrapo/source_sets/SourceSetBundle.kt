package com.nikitakrapo.source_sets

import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty

data class SourceSetBundle(
    val main: KotlinSourceSet,
    val test: KotlinSourceSet,
)


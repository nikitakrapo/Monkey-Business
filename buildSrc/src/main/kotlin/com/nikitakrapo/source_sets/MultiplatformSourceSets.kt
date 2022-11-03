package com.nikitakrapo.source_sets

import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

interface MultiplatformSourceSets : NamedDomainObjectContainer<KotlinSourceSet> {
    val common: SourceSetBundle
}
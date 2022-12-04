package com.nikitakrapo.sourcesets

import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

interface MultiplatformSourceSets : NamedDomainObjectContainer<KotlinSourceSet> {
    val common: SourceSetBundle
}

package com.nikitakrapo.sourcesets

import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

class DefaultMultiplatformSourceSets(
    private val sourceSets: NamedDomainObjectContainer<KotlinSourceSet>
) : MultiplatformSourceSets, NamedDomainObjectContainer<KotlinSourceSet> by sourceSets {
    override val common by bundle()
}

package com.nikitakrapo.modules_setup

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.gradle.api.Project
import com.nikitakrapo.source_sets.DefaultMultiplatformSourceSets
import com.nikitakrapo.source_sets.MultiplatformSourceSets

fun Project.setupMultiplatformModule(
    targets: KotlinMultiplatformExtension.() -> Unit = ::multiplatformDefaultTargets,
) {
    multiplatformExtension.apply {
        targets()

        setupSourceSets {
            common.main.dependencies {
                implementation(kotlin("stdlib"))
            }

            common.test.dependencies {
                implementation(kotlin("test"))
            }
        }

        if (isAndroidTargetEnabled()) {
            setupAndroidLibrary()
        }
    }
}

internal fun KotlinMultiplatformExtension.setupSourceSets(block: MultiplatformSourceSets.() -> Unit) {
    DefaultMultiplatformSourceSets(sourceSets).block()
}

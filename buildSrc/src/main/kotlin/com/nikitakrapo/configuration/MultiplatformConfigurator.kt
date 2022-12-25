package com.nikitakrapo.configuration

import com.nikitakrapo.sourcesets.DefaultMultiplatformSourceSets
import com.nikitakrapo.sourcesets.MultiplatformSourceSets
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.setupMultiplatformModule(
    withUtils: Boolean = false,
    targets: KotlinMultiplatformExtension.() -> Unit = ::multiplatformDefaultTargets,
) {
    multiplatformExtension.apply {
        targets()

        setupSourceSets {
            common.main.dependencies {
                if (withUtils) { implementation(project(":features:kmmutils")) }
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

package com.nikitakrapo.configuration.multiplatform

import com.nikitakrapo.configuration.android.setupAndroidLibrary
import com.nikitakrapo.configuration.libs
import com.nikitakrapo.sourcesets.DefaultMultiplatformSourceSets
import com.nikitakrapo.sourcesets.MultiplatformSourceSets
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.setupMultiplatformModule(
    withUtils: Boolean = false,
    targets: KotlinMultiplatformExtension.() -> Unit,
) {
    multiplatformExtension.apply {
        targets()

        setupSourceSets {
            common.main.dependencies {
                if (withUtils) { implementation(project(":features:kmm-utils")) }
                implementation(libs.napier)
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

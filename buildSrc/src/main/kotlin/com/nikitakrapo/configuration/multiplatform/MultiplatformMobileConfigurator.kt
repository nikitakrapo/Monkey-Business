package com.nikitakrapo.configuration.multiplatform

import com.nikitakrapo.configuration.android.androidModuleConfig
import com.nikitakrapo.configuration.android.AndroidConfigurator
import com.nikitakrapo.configuration.android.setupAndroidLibrary
import com.nikitakrapo.configuration.libs
import com.nikitakrapo.sourcesets.DefaultMultiplatformSourceSets
import org.gradle.api.Project

class MultiplatformMobileConfigurator(
    private val project: Project,
) {
    internal fun configureMulitplatformModule() {
        project.multiplatformExtension.apply {
            DefaultMultiplatformSourceSets(sourceSets).apply {
                common.main.dependencies {
                    implementation(project.libs.napier)
                    implementation(kotlin("stdlib"))
                }

                common.test.dependencies {
                    implementation(kotlin("test"))
                }
            }
        }
    }

    fun android(
        namespace: String,
        configuration: AndroidConfigurator.() -> Unit = {},
    ) {
        project.multiplatformExtension.android()
        project.setupAndroidLibrary(
            moduleConfig = project.androidModuleConfig(namespace = namespace),
            configuration = configuration
        )
    }

    fun ios() {
        project.multiplatformExtension.iosCompat()
    }
}

/**
 * Sets up iOS and Android
 */
fun Project.setupMobileMultiplatformModule(
    androidNamespace: String,
    androidConfiguration: AndroidConfigurator.() -> Unit = {}
) {
    setupMobileMultiplatformModule {
        ios()
        android(namespace = androidNamespace, configuration = androidConfiguration)
    }
}

fun Project.setupMobileMultiplatformModule(
    configuration: MultiplatformMobileConfigurator.() -> Unit,
) {
    val configurator = MultiplatformMobileConfigurator(project = this)
    configurator.configureMulitplatformModule()
    configurator.apply(configuration)
}

package com.nikitakrapo.configuration.android

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.nikitakrapo.configuration.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidAppConfigurator(
    private val appConfig: AndroidApplicationConfig,
    private val moduleConfig: AndroidModuleConfig,
    private val project: Project,
) : AndroidConfigurator(moduleConfig, project) {

    internal fun configureApplication() {
        configureAndroidModule()
        println("Configuring application with $appConfig")
        project.dependencies {
            add("implementation", project.libs.napier)
        }
        project.configure<BaseAppModuleExtension> {
            defaultConfig {
                applicationId = appConfig.applicationId
                versionCode = appConfig.versionCode
                versionName = appConfig.versionName
            }
        }
    }

    fun setResourceConfigs(locales: List<String>) {
        project.configure<BaseAppModuleExtension> {
            defaultConfig.resourceConfigurations += locales
        }
    }
}

fun Project.setupAndroidApp(
    appConfig: AndroidApplicationConfig,
    moduleConfig: AndroidModuleConfig,
    configuration: AndroidAppConfigurator.() -> Unit,
) {
    AndroidAppConfigurator(
        appConfig = appConfig,
        moduleConfig = moduleConfig,
        project = this
    ).apply {
        configureApplication()
        configuration()
    }
}

package com.nikitakrapo.configuration.android

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.nikitakrapo.configuration.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withGroovyBuilder

open class AndroidConfigurator(
    private val config: AndroidModuleConfig,
    private val project: Project,
) {

    internal fun configureAndroidModule() {
        project.extensions.configure<BaseExtension> {
            namespace = config.namespace

            compileSdkVersion(config.compileSdk)

            defaultConfig {
                minSdk = config.minSdk
                targetSdk = config.targetSdk

                buildFeatures.buildConfig = true
            }

            compileOptions {
                sourceCompatibility(JavaVersion.VERSION_1_8)
                targetCompatibility(JavaVersion.VERSION_1_8)
            }

            withGroovyBuilder {
                "kotlinOptions" {
                    setProperty("jvmTarget", "1.8")
                }
            }
        }
    }

    fun useCompose() {
        project.extensions.configure<BaseExtension> {
            buildFeatures.compose = true
            composeOptions {
                kotlinCompilerExtensionVersion = project.libs.versions.kotlinCompilerCompose.get()
            }
        }
    }

    fun configureBenchmarkBuildType() {
        project.configure<BaseExtension> {
            buildTypes {
                create("benchmark") {
                    signingConfig = signingConfigs.getByName("debug")
                    matchingFallbacks += listOf("release")
                    isDebuggable = false
                }
            }
        }
    }
}

fun Project.setupAndroidLibrary(
    moduleConfig: AndroidModuleConfig,
    configuration: AndroidConfigurator.() -> Unit
) {
    AndroidConfigurator(
        config = moduleConfig,
        project = this
    ).apply {
        configureAndroidModule()
        configuration()
    }
}

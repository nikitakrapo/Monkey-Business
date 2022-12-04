package com.nikitakrapo.configuration

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withGroovyBuilder

internal fun defaultAndroidConfig() = AndroidConfig(
    minSdk = 24,
    targetSdk = 31,
    compileSdk = 33
)

fun Project.setupAndroidLibrary() {
    setupAndroidCommon()
}

fun Project.setupAndroidApp(
    applicationId: String,
    versionCode: Int,
    versionName: String
) {
    setupAndroidCommon()

    extensions.configure<BaseAppModuleExtension> {
        defaultConfig {
            this.applicationId = applicationId
            this.versionCode = versionCode
            this.versionName = versionName
        }
    }
}

internal fun Project.setupAndroidCommon(
    config: AndroidConfig = defaultAndroidConfig()
) {
    extensions.configure<BaseExtension> {
        compileSdkVersion(config.compileSdk)

        defaultConfig {
            minSdk = config.minSdk
            targetSdk = config.targetSdk
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

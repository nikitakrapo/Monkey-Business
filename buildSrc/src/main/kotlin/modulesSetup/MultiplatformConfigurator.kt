package modulesSetup

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

fun Project.setupMultiplatformModule(
    configure: KotlinMultiplatformExtension.() -> Unit,
) {
    multiplatformExtension.apply {
        configure()

        if (isAndroidTargetEnabled()) {
            setupAndroidLibrary()
        }
    }
}

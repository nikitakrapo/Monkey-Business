import com.nikitakrapo.modules_setup.iosCompat
import com.nikitakrapo.modules_setup.setupMultiplatformModule

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

setupMultiplatformModule()

kotlin {
    cocoapods {
        name = "FinancialModels"
        summary = "Shared financial models"
        homepage = "https://github.com/nikitakrapo/Monkey-Business"
        ios.deploymentTarget = "14.1"
        framework {
            isStatic = true
            baseName = "financialModels"
        }
    }
    
    sourceSets {
        val commonMain by getting
        val androidMain by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

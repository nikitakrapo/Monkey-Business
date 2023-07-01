import com.nikitakrapo.configuration.android.androidModuleConfig
import com.nikitakrapo.configuration.android.setupAndroidLibrary

plugins {
    id("com.android.library")
    kotlin("android")
}

setupAndroidLibrary(
    moduleConfig = androidModuleConfig("com.nikitakrapo.monkeybusiness.resources"),
    configuration = {}
)

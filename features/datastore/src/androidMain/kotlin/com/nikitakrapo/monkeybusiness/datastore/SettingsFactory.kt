package com.nikitakrapo.monkeybusiness.datastore

import com.nikitakrapo.application.PlatformContext
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual fun createSettingsFactory(platformContext: PlatformContext): Settings.Factory {
    return SharedPreferencesSettings.Factory(platformContext.context)
}
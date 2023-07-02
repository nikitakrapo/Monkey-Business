package com.nikitakrapo.monkeybusiness.datastore

import com.nikitakrapo.application.PlatformContext
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings

actual fun createSettingsFactory(platformContext: PlatformContext): Settings.Factory {
    return NSUserDefaultsSettings.Factory()
}
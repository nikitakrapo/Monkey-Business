package com.nikitakrapo.monkeybusiness.datastore

import com.nikitakrapo.application.PlatformContext
import com.russhwolf.settings.Settings

expect fun createSettingsFactory(platformContext: PlatformContext): Settings.Factory
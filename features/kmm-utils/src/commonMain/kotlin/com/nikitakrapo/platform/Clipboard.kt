package com.nikitakrapo.platform

import com.nikitakrapo.application.PlatformContext

expect fun PlatformContext.copyToClipboard(text: String, label: String? = null)
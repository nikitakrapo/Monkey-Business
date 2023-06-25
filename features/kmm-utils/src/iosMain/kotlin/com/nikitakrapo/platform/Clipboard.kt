package com.nikitakrapo.platform

import com.nikitakrapo.application.PlatformContext
import platform.UIKit.UIPasteboard

actual fun PlatformContext.copyToClipboard(text: String, label: String?) {
    UIPasteboard.generalPasteboard().setString(text)
}
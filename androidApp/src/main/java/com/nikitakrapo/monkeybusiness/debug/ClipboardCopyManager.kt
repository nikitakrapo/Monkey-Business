package com.nikitakrapo.monkeybusiness.debug

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

object ClipboardCopyManager {
    fun Context.copyToClipboard(
        label: String,
        text: String,
    ) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
        val clip: ClipData = ClipData.newPlainText(label, text)
        clipboard?.setPrimaryClip(clip)
    }
}

package com.nikitakrapo.platform

import android.app.Service
import android.content.ClipData
import android.content.ClipData.Item
import android.content.ClipDescription
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import com.nikitakrapo.application.PlatformContext

actual fun PlatformContext.copyToClipboard(text: String, label: String?) {
    val cm = context.getSystemService(Service.CLIPBOARD_SERVICE) as ClipboardManager
    val item = Item(text)
    val clipDescription = ClipDescription(
        label.orEmpty(),
        arrayOf(MIMETYPE_TEXT_PLAIN)
    )
    val clipData = ClipData(
        clipDescription,
        item,
    )
    cm.setPrimaryClip(clipData)
}
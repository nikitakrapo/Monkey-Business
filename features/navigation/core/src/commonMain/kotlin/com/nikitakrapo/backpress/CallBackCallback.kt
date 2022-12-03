package com.nikitakrapo.backpress

internal fun Iterable<BackCallback>.call(): Boolean {
    lastOrNull(BackCallback::isEnabled)?.also {
        it.onBack()
        return true
    }

    return false
}

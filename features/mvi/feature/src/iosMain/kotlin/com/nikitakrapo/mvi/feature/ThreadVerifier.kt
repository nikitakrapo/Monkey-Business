package com.nikitakrapo.mvi.feature

import platform.Foundation.NSThread

internal actual fun ThreadVerifier(): ThreadVerifier {
    val origin = NSThread.currentThread.name
    return ThreadVerifier { component ->
        val current = NSThread.currentThread.name
        check(origin == current) {
            "$component invoked on wrong thread, expected: [$origin] but was [$current]"
        }
    }
}

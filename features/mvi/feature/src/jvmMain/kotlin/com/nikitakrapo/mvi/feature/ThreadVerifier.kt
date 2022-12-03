package com.nikitakrapo.mvi.feature

internal actual fun ThreadVerifier(): ThreadVerifier {
    val originId = Thread.currentThread().id
    val originName = Thread.currentThread().name
    return ThreadVerifier { component ->
        val id = Thread.currentThread().id
        val name = Thread.currentThread().name
        check(originId == id) {
            "$component invoked on wrong thread, expected: [$originId] ($originName) but was [$id] ($name)"
        }
    }
}

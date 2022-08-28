package ru.yandex.lavka.mvi.feature

fun interface ThreadVerifier {
    @Throws(IllegalStateException::class)
    fun verify(component: String)
}

internal expect fun ThreadVerifier() : ThreadVerifier

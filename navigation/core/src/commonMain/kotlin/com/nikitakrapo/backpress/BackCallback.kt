package com.nikitakrapo.backpress

abstract class BackCallback(
    isEnabled: Boolean = true,
) {
    private var enableChangeCallbacks = emptySet<(Boolean) -> Unit>()

    var isEnabled: Boolean = isEnabled
        set(value) {
            field = value
            enableChangeCallbacks.forEach { it(value) }
        }

    fun registerEnabledChangedCallback(callback: (isEnabled: Boolean) -> Unit) {
        check (callback !in enableChangeCallbacks) { "Callback already registered" }
        this.enableChangeCallbacks += callback
    }

    fun unregisterEnabledChangedCallback(callback: (isEnabled: Boolean) -> Unit) {
        check (callback in enableChangeCallbacks) { "Callback not registered" }
        this.enableChangeCallbacks -= callback
    }

    abstract fun onBack()
}

fun BackCallback(
    isEnabled: Boolean = true,
    onBack: () -> Unit,
): BackCallback =
    object : BackCallback(isEnabled = isEnabled) {
        override fun onBack() {
            onBack.invoke()
        }
    }

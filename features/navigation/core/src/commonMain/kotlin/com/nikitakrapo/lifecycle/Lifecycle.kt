package com.nikitakrapo.lifecycle

interface Lifecycle {

    val state: State

    fun registerCallbacks(callbacks: LifecycleCallbacks)

    fun unregisterCallbacks(callbacks: LifecycleCallbacks)

    enum class State {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED
    }
}

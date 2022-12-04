package com.nikitakrapo.navigation

fun <T : Any> StackNavigation() = object : StackNavigation<T> {

    private val callbacks = mutableSetOf<(StackNavigationEvent<T>) -> Unit>()

    override fun registerNavigationCallback(callback: (StackNavigationEvent<T>) -> Unit) {
        check(callback !in callbacks) { "Callback already registered" }
        callbacks += callback
    }

    override fun unregisterNavigationCallback(callback: (StackNavigationEvent<T>) -> Unit) {
        check(callback in callbacks) { "Callback not registered" }
        callbacks -= callback
    }

    override fun navigate(
        transformation: (stack: List<T>) -> List<T>,
        onComplete: (oldStack: List<T>, newStack: List<T>) -> Unit
    ) {
        callbacks.forEach { it.invoke(StackNavigationEvent(transformation, onComplete)) }
    }
}

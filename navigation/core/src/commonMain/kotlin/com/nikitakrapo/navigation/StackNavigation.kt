package com.nikitakrapo.navigation

interface StackNavigation<T : Any> {

    fun registerNavigationCallback(callback: (StackNavigationEvent<T>) -> Unit)

    fun unregisterNavigationCallback(callback: (StackNavigationEvent<T>) -> Unit)

    fun navigate(
        transformation: (stack: List<T>) -> List<T>,
        onComplete: (oldStack: List<T>, newStack: List<T>) -> Unit,
    )
}

package com.nikitakrapo.navigation.stack

interface StackNavigator<T> {
    fun navigate(
        transformation: (stack: List<T>) -> List<T>,
        onComplete: (oldStack: List<T>, newStack: List<T>) -> Unit
    )
}
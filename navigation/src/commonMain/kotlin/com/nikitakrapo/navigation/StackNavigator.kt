package com.nikitakrapo.navigation

interface StackNavigator<T> {

    fun navigate(
        transformation: (oldStack: List<T>) -> List<T>,
        onComplete: (oldStack: List<T>, newStack: List<T>) -> Unit,
    )
}
package com.nikitakrapo.navigation

class StackNavigationEvent<T : Any>(
    val transformation: (stack: List<T>) -> List<T>,
    val onComplete: (oldStack: List<T>, newStack: List<T>) -> Unit,
)
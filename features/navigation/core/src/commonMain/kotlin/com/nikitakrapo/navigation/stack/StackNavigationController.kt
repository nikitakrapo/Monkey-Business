package com.nikitakrapo.navigation.stack

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StackNavigationController<out C : Any, out T : Any>(
    initialStack: () -> ChildStack<C, T>,
) {
    private val stackFlow = MutableStateFlow(initialStack())
    val stack: StateFlow<ChildStack<C, T>> get() = stackFlow.asStateFlow()
}
package com.nikitakrapo.navigation.stack

import com.nikitakrapo.navigation.Child

data class ChildStack<out C : Any, out T : Any>(
    val active: Child.Created<C, T>,
    val backStack: List<Child<C, T>> = emptyList(),
)
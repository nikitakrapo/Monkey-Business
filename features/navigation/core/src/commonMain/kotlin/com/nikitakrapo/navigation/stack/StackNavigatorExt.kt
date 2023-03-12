package com.nikitakrapo.navigation.stack

import com.arkivanov.decompose.router.stack.StackNavigator

fun <T : Any> StackNavigator<T>.filterNot(
    filter: (T) -> Boolean,
    onComplete: (newStack: List<T>, oldStack: List<T>) -> Unit = { _, _ -> },
) = filter(
    filter = { !filter(it) },
    onComplete
)

fun <T : Any> StackNavigator<T>.filter(
    filter: (T) -> Boolean,
    onComplete: (newStack: List<T>, oldStack: List<T>) -> Unit = { _, _ -> },
) = navigate(
    transformer = { stack ->
        stack.filter(filter)
    },
    onComplete = onComplete,
)
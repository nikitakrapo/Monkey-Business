package com.nikitakrapo.navigation.stack

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigationSource
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

inline fun <reified C : Parcelable, T : Any> ComponentContext.childFlow(
    source: StackNavigationSource<C>,
    initialConfiguration: C,
    key: String = "DefaultChildStack",
    handleBackButton: Boolean = false,
    noinline childFactory: (configuration: C, ComponentContext) -> T,
): StateFlow<T> {
    val stackValue = childStack(
        source = source,
        initialConfiguration = initialConfiguration,
        key = key,
        handleBackButton = handleBackButton,
        childFactory = childFactory,
    )
    val mutableStackFlow = MutableStateFlow(stackValue.value.active.instance)
    stackValue
        .map(ChildStack<C, T>::active)
        .subscribe { mutableStackFlow.value = it.instance }
    return mutableStackFlow.asStateFlow()
}

inline fun <reified C : Parcelable, T : Any> ComponentContext.childStackFlow(
    source: StackNavigationSource<C>,
    initialConfiguration: C,
    key: String = "DefaultChildStack",
    handleBackButton: Boolean = false,
    noinline childFactory: (configuration: C, ComponentContext) -> T,
): StateFlow<ChildStack<C, T>> {
    val stackValue = childStack(
        source = source,
        initialConfiguration = initialConfiguration,
        key = key,
        handleBackButton = handleBackButton,
        childFactory = childFactory,
    )
    val mutableStackFlow = MutableStateFlow(stackValue.value)
    stackValue.subscribe { mutableStackFlow.value = it }
    return mutableStackFlow.asStateFlow()
}

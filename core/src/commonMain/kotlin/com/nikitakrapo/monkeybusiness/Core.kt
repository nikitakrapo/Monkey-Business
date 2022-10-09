package com.nikitakrapo.monkeybusiness

import kotlinx.coroutines.flow.StateFlow

interface Core {

    val state: StateFlow<State>

    fun onHomeClicked()
    fun onProfileClicked()

    sealed class Child {
        object Home : Child()
        object Profile : Child()
    }

    data class State(
        val child: Child,
    )
}
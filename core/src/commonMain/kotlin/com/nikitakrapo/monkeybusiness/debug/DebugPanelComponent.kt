package com.nikitakrapo.monkeybusiness.debug

import kotlinx.coroutines.flow.StateFlow

interface DebugPanelComponent {

    val state: StateFlow<State>

    fun open()
    fun close()

    fun copyUid()
    fun copyAuthToken()

    data class State(
        val panelOpened: Boolean,
    )
}
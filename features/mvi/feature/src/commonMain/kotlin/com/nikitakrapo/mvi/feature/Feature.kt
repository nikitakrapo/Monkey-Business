package com.nikitakrapo.mvi.feature

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface Feature<in Intent : Any, out State : Any, out Event : Any> {

    val state: StateFlow<State>

    val events: SharedFlow<Event>

    val isDisposed: Boolean

    fun accept(intent: Intent)

    fun dispose()
}

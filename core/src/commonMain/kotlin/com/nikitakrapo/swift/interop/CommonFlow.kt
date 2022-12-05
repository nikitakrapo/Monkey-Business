package com.nikitakrapo.swift.interop

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CommonStateFlow<T : Any>(private val origin: StateFlow<T>) : StateFlow<T> by origin {
    fun watch(block: (T) -> Unit): Cancelable = watchFlow(block)
}

class CommonSharedFlow<T : Any?>(private val origin: SharedFlow<T>) : SharedFlow<T> by origin {
    fun watch(block: (T) -> Unit): Cancelable = watchFlow(block)
}

private fun <T> Flow<T>.watchFlow(block: (T) -> Unit): Cancelable {
    val context = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    onEach(block).launchIn(context)

    return object : Cancelable {
        override fun cancel() {
            context.cancel()
        }
    }
}
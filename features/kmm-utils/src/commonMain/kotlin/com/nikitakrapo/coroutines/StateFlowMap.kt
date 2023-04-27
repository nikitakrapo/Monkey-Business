package com.nikitakrapo.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

fun <T, R> StateFlow<T>.mapState(
    scope: CoroutineScope,
    transformer: (T) -> R
) = map(transformer).stateIn(
    scope = scope,
    started = SharingStarted.Eagerly,
    initialValue = transformer(value)
)
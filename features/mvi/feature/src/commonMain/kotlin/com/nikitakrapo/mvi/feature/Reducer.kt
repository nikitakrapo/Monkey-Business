package com.nikitakrapo.mvi.feature

fun interface Reducer<State : Any, in Effect : Any> {

    operator fun State.invoke(effect: Effect): State
}

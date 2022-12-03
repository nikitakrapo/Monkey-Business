package com.nikitakrapo.mvi.feature

fun interface EventsPublisher<in Action : Any, in Effect : Any, in State : Any, out Event : Any> {

    operator fun invoke(action: Action, effect: Effect, state: State): Event?
}

package ru.yandex.lavka.mvi.feature

import kotlinx.coroutines.flow.Flow

fun interface Actor<in Action : Any, in State : Any, out Effect : Any> {

    operator fun invoke(action: Action, state: State): Flow<Effect>
}

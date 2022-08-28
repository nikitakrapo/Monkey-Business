package ru.yandex.lavka.mvi.feature

import kotlinx.coroutines.flow.Flow

fun interface Bootstrapper<out Action : Any> {

    operator fun invoke(): Flow<Action>
}

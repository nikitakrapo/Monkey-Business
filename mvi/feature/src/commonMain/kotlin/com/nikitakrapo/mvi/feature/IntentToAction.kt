package com.nikitakrapo.mvi.feature

fun interface IntentToAction<in Intent : Any, out Action : Any> {

    operator fun invoke(intent: Intent): Action
}

package com.nikitakrapo.coroutines

import com.nikitakrapo.foundation.toException
import platform.Foundation.NSError
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

fun <T> Continuation<T>.completionHandler() = completionHandler<T, T> { it }

fun Continuation<Unit>.noArgCompletionHandler(): (error: NSError?) -> Unit = { error ->
    complete(Unit, error)
}

fun <T, R> Continuation<R>.completionHandler(
    valueMapping: (T?) -> R?,
): (value: T?, error: NSError?) -> Unit = { value, error ->
    complete(value?.let { valueMapping(it) }, error)
}

fun <T> Continuation<T>.complete(
    value: T?,
    error: NSError?,
) = when {
    error != null -> resumeWithException(error.toException())
    value != null -> resume(value)
    else -> resumeWithException(IllegalStateException("Both result & error null"))
}

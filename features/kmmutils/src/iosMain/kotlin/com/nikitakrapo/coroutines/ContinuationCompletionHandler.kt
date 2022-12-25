package com.nikitakrapo.coroutines

import com.nikitakrapo.foundation.toException
import kotlinx.coroutines.CancellableContinuation
import platform.Foundation.NSError
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

fun <T> CancellableContinuation<T>.completionHandler() = completionHandler<T, T> { it }

fun CancellableContinuation<Unit>.noArgCompletionHandler(
): (error: NSError?) -> Unit = { error ->
    complete(Unit, error)
}

fun <T, R> CancellableContinuation<R>.completionHandler(
    valueMapping: (T?) -> R?,
): (value: T?, error: NSError?) -> Unit = { value, error ->
    complete(value?.let { valueMapping(it) }, error)
}

fun <T> CancellableContinuation<T>.complete(
    value: T?,
    error: NSError?,
) = when {
    error != null -> resumeWithException(error.toException())
    value != null -> resume(value)
    else -> resumeWithException(IllegalStateException("Both result & error null"))
}

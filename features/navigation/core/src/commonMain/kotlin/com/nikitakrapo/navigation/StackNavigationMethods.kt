package com.nikitakrapo.navigation

fun <T : Any> StackNavigation<T>.pop(onComplete: (Boolean) -> Unit) {
    navigate(
        transformation = { stack -> stack.takeIf { it.size > 1 }?.dropLast(1) ?: stack },
        onComplete = { oldStack, newStack -> onComplete(oldStack.size > newStack.size) }
    )
}

fun <T : Any> StackNavigation<T>.popWhile(
    predicate: (T) -> Boolean,
    onComplete: (isSuccess: Boolean) -> Unit = {},
) {
    navigate(
        transformation = { stack -> stack.dropLastWhile(predicate) },
        onComplete = { newStack, oldStack -> onComplete(newStack.size < oldStack.size) },
    )
}

fun <T : Any> StackNavigation<T>.bringToFront(item: T, onComplete: () -> Unit = {}) {
    navigate(
        transformation = { stack -> stack.filterNot { it::class == item::class } + item },
        onComplete = { _, _ -> onComplete() },
    )
}

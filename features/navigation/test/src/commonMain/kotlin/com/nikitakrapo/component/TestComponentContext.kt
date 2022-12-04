package com.nikitakrapo.component

import com.nikitakrapo.backpress.BackCallback
import com.nikitakrapo.backpress.BackHandler
import com.nikitakrapo.lifecycle.Lifecycle
import com.nikitakrapo.lifecycle.LifecycleCallbacks

fun TestComponentContext(
    lifecycleState: Lifecycle.State = Lifecycle.State.CREATED
) = object : ComponentContext {
    override val lifecycle: Lifecycle = object : Lifecycle {
        override val state: Lifecycle.State = lifecycleState

        override fun registerCallbacks(callbacks: LifecycleCallbacks) {}

        override fun unregisterCallbacks(callbacks: LifecycleCallbacks) {}
    }

    override val backHandler: BackHandler = object : BackHandler {
        override fun register(callback: BackCallback) {}

        override fun unregister(callback: BackCallback) {}
    }
}

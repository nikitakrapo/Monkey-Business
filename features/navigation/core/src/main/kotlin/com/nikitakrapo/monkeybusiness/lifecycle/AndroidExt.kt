package com.nikitakrapo.monkeybusiness.lifecycle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import com.nikitakrapo.lifecycle.Lifecycle
import com.nikitakrapo.lifecycle.LifecycleCallbacks
import androidx.lifecycle.Lifecycle as AndroidLifecycle
import androidx.lifecycle.LifecycleOwner as AndroidLifecycleOwner

fun AndroidLifecycle.asCommonLifecycle(): Lifecycle = CommonLifecycle(this)

fun AndroidLifecycleOwner.commonLifecycle() = lifecycle.asCommonLifecycle()

private class CommonLifecycle(
    private val androidLifecycle: AndroidLifecycle
) : Lifecycle {

    private val observerMap = HashMap<LifecycleCallbacks, LifecycleObserver>()

    override val state: Lifecycle.State get() = androidLifecycle.currentState.asCommonState()

    override fun registerCallbacks(callbacks: LifecycleCallbacks) {
        check(callbacks !in observerMap) { "Callback already exists" }

        val observer = AndroidLifecycleObserver(delegate = callbacks, onDestroy = { observerMap -= callbacks })
        observerMap[callbacks] = observer
        androidLifecycle.addObserver(observer)
    }

    override fun unregisterCallbacks(callbacks: LifecycleCallbacks) {
        observerMap.remove(callbacks)?.also {
            androidLifecycle.removeObserver(it)
        }
    }
}

fun AndroidLifecycle.State.asCommonState() = when (this) {
    AndroidLifecycle.State.DESTROYED -> Lifecycle.State.DESTROYED
    AndroidLifecycle.State.INITIALIZED -> Lifecycle.State.INITIALIZED
    AndroidLifecycle.State.CREATED -> Lifecycle.State.CREATED
    AndroidLifecycle.State.STARTED -> Lifecycle.State.STARTED
    AndroidLifecycle.State.RESUMED -> Lifecycle.State.RESUMED
}

private class AndroidLifecycleObserver(
    private val delegate: LifecycleCallbacks,
    private val onDestroy: () -> Unit
) : DefaultLifecycleObserver {
    override fun onCreate(owner: androidx.lifecycle.LifecycleOwner) {
        delegate.onCreate()
    }

    override fun onStart(owner: androidx.lifecycle.LifecycleOwner) {
        delegate.onStart()
    }

    override fun onResume(owner: androidx.lifecycle.LifecycleOwner) {
        delegate.onResume()
    }

    override fun onPause(owner: androidx.lifecycle.LifecycleOwner) {
        delegate.onPause()
    }

    override fun onStop(owner: androidx.lifecycle.LifecycleOwner) {
        delegate.onStop()
    }

    override fun onDestroy(owner: androidx.lifecycle.LifecycleOwner) {
        delegate.onDestroy()
        onDestroy.invoke()
    }
}

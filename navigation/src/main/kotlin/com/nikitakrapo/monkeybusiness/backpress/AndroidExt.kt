package com.nikitakrapo.monkeybusiness.backpress

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.addCallback
import com.nikitakrapo.backpress.BackCallback
import com.nikitakrapo.backpress.BackHandler
import com.nikitakrapo.backpress.call

fun OnBackPressedDispatcher.asBackHandler(): BackHandler = AndroidBackHandler(this)

fun OnBackPressedDispatcherOwner.backHandler() = onBackPressedDispatcher.asBackHandler()

private class AndroidBackHandler(
    androidBackPressDispatcher: OnBackPressedDispatcher,
) : BackHandler {

    private var set = emptySet<BackCallback>()
    private val delegateCallback = androidBackPressDispatcher.addCallback(enabled = false) { set.call() }
    private val enabledChangedListener: (Boolean) -> Unit = { onEnabledChanged() }

    override fun register(callback: BackCallback) {
        check(callback !in set) { "Callback is already registered" }

        this.set += callback
        callback.registerEnabledChangedCallback(enabledChangedListener)
        onEnabledChanged()
    }

    override fun unregister(callback: BackCallback) {
        check(callback in set) { "Callback is not registered" }

        callback.unregisterEnabledChangedCallback(enabledChangedListener)
        this.set -= callback
        onEnabledChanged()
    }

    private fun onEnabledChanged() {
        delegateCallback.isEnabled = set.any(BackCallback::isEnabled)
    }
}

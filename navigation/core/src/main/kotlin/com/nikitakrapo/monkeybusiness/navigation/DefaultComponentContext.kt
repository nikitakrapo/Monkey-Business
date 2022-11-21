package com.nikitakrapo.monkeybusiness.navigation

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.OnBackPressedDispatcherOwner
import com.nikitakrapo.backpress.BackHandler
import com.nikitakrapo.component.ComponentContext
import com.nikitakrapo.lifecycle.Lifecycle
import com.nikitakrapo.monkeybusiness.backpress.asBackHandler
import com.nikitakrapo.monkeybusiness.lifecycle.asCommonLifecycle
import androidx.lifecycle.Lifecycle as AndroidLifecycle
import androidx.lifecycle.LifecycleOwner as AndroidLifecycleOwner

fun <T> T.defaultComponentContext() where
        T : AndroidLifecycleOwner, T : OnBackPressedDispatcherOwner =
    DefaultComponentContext(
        androidLifecycle = lifecycle,
        onBackPressedDispatcher = onBackPressedDispatcher,
    )

class DefaultComponentContext(
    androidLifecycle: AndroidLifecycle,
    onBackPressedDispatcher: OnBackPressedDispatcher,
) : ComponentContext {

    override val lifecycle: Lifecycle = androidLifecycle.asCommonLifecycle()

    override val backHandler: BackHandler = onBackPressedDispatcher.asBackHandler()
}
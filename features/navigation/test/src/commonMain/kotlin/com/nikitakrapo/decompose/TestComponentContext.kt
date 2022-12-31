package com.nikitakrapo.decompose

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.decompose.statekeeper.TestStateKeeperDispatcher
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.instancekeeper.InstanceKeeperDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.parcelable.ParcelableContainer

class TestComponentContext(
    savedState: ParcelableContainer? = null,
    override val instanceKeeper: InstanceKeeperDispatcher = InstanceKeeperDispatcher(),
) : ComponentContext {
    override val lifecycle: LifecycleRegistry = LifecycleRegistry()
    override val stateKeeper: TestStateKeeperDispatcher = TestStateKeeperDispatcher(savedState)
    override val backHandler: BackDispatcher = BackDispatcher()
}

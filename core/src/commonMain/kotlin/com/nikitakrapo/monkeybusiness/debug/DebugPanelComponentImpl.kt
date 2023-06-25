package com.nikitakrapo.monkeybusiness.debug

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.application.PlatformContext
import com.nikitakrapo.decompose.coroutines.coroutineScope
import com.nikitakrapo.platform.copyToClipboard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DebugPanelComponentImpl(
    private val accountManager: AccountManager,
    private val platformContext: PlatformContext,
    componentContext: ComponentContext,
) : DebugPanelComponent, ComponentContext by componentContext {

    private val scope = coroutineScope(Dispatchers.Main)

    private val stateFlow = MutableStateFlow(
        DebugPanelComponent.State(
            panelOpened = false,
        )
    )
    override val state: StateFlow<DebugPanelComponent.State> = stateFlow.asStateFlow()

    override fun open() {
        stateFlow.value = stateFlow.value.copy(panelOpened = true)
    }

    override fun close() {
        stateFlow.value = stateFlow.value.copy(panelOpened = false)
    }

    override fun copyUid() {
        scope.launch {
            val uid = accountManager.account.value?.uid ?: "null"
            platformContext.copyToClipboard(text = uid)
        }
    }

    override fun copyAuthToken() {
        scope.launch {
            val token = accountManager.getToken().getOrNull()
            token?.let { platformContext.copyToClipboard(text = it) }
        }
    }
}
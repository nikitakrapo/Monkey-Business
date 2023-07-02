package com.nikitakrapo.monkeybusiness.profile

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.account.currentAccount
import com.nikitakrapo.account.models.getDisplayName
import com.nikitakrapo.decompose.coroutines.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileComponentImpl(
    componentContext: ComponentContext,
    private val dependencies: ProfileDependencies,
) : ProfileComponent, ComponentContext by componentContext {

    private val account get() = dependencies.accountManager.currentAccount

    private val scope = coroutineScope(Dispatchers.Main)

    private val stateFlow = MutableStateFlow(
        ProfileComponent.State(
            displayName = account?.getDisplayName() ?: "ERROR",
            profileImageUrl = account?.photoUrl,
        ),
    )
    override val state: StateFlow<ProfileComponent.State>
        get() = stateFlow.asStateFlow()

    init {
        scope.launch {
            dependencies.accountManager.account.collect {
                stateFlow.value = state.value.copy(displayName = it?.getDisplayName() ?: "")
            }
        }
    }

    override fun onEditClicked() {
        dependencies.profileEditRouter.openProfileEdit()
    }

    override fun onSettingsClicked() {
        dependencies.settingsRouter.openSettings()
    }

    override fun onLogoutClicked() {
        dependencies.accountManager.logout()
    }
}

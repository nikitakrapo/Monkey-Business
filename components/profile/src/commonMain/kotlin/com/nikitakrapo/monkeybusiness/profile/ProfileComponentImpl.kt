package com.nikitakrapo.monkeybusiness.profile

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.account.Account
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileComponentImpl(
    componentContext: ComponentContext,
    private val dependencies: ProfileDependencies,
) : ProfileComponent, ComponentContext by componentContext {
    override val state: StateFlow<ProfileComponent.State>
        get() = MutableStateFlow(
            ProfileComponent.State(
                //TODO: somehow make account determined at this state
                email = (dependencies.accountManager.currentAccount.value as Account.Emailish).email,
                username = null,
                profileImageUrl = null
            )
        )

    override fun onLogoutClicked() {
        dependencies.accountManager.logout()
    }
}

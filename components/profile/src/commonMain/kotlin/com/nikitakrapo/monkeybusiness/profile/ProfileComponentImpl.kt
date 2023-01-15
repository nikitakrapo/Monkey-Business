package com.nikitakrapo.monkeybusiness.profile

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.account.models.Account
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileComponentImpl(
    componentContext: ComponentContext,
    private val account: Account,
    private val dependencies: ProfileDependencies,
) : ProfileComponent, ComponentContext by componentContext {
    override val state: StateFlow<ProfileComponent.State>
        get() = MutableStateFlow(
            ProfileComponent.State(
                email = account.email,
                username = account.username,
                profileImageUrl = account.photoUrl,
            )
        )

    override fun onLogoutClicked() {
        dependencies.accountManager.logout()
    }
}

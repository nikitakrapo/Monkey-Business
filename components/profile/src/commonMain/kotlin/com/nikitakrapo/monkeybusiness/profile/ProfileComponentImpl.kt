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
                displayName = account.getDisplayName(),
                profileImageUrl = account.photoUrl,
            )
        )

    override fun onEditClicked() {
        dependencies.profileEditRouter.openProfileEdit()
    }

    override fun onLogoutClicked() {
        dependencies.accountManager.logout()
    }

    private fun Account.getDisplayName(): String {
        username?.let { return it }
        val atCharIndex = email.indexOf(char = '@')
        return if (atCharIndex != -1) email.substring(0..atCharIndex) else email
    }
}

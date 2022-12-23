package com.nikitakrapo.monkeybusiness.profile.profiledetails

import com.nikitakrapo.account.Account
import com.nikitakrapo.account.AccountManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileDetailsComponentImpl(
    private val accountManager: AccountManager,
    private val account: Account.Emailish,
) : ProfileDetailsComponent {
    override val state: StateFlow<ProfileDetailsComponent.State>
        get() = MutableStateFlow(ProfileDetailsComponent.State(account.email, null, null))

    override fun onLogoutClicked() {
        accountManager.logout()
    }
}
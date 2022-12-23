package com.nikitakrapo.monkeybusiness.profile.profiledetails

import com.nikitakrapo.account.AccountManager

class ProfileDetailsComponentImpl(
    private val accountManager: AccountManager,
) : ProfileDetailsComponent {
    override fun onLogoutClicked() {
        accountManager.logout()
    }
}
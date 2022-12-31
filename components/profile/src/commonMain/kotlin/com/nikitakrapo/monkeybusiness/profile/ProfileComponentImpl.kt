package com.nikitakrapo.monkeybusiness.profile

import com.nikitakrapo.account.Account
import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.monkeybusiness.profile.auth.AuthComponentImpl
import com.nikitakrapo.monkeybusiness.profile.profiledetails.ProfileDetailsComponentImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileComponentImpl(
    private val onBackClicked: () -> Unit,
    private val accountManager: AccountManager,
) : ProfileComponent {

    // TODO: fix
    private val scope = CoroutineScope(Dispatchers.Main)

    private val stateFlow = MutableStateFlow(
        ProfileComponent.State(
            getInitialChild(accountManager.currentAccount.value)
        )
    )
    override val state: StateFlow<ProfileComponent.State> get() = stateFlow.asStateFlow()

    override fun onBackArrowClicked() {
        onBackClicked()
    }

    init {
        scope.launch {
            accountManager.currentAccount.collect {
                stateFlow.value = ProfileComponent.State(getInitialChild(it))
            }
        }
    }

    private fun getInitialChild(account: Account?): ProfileComponent.Child =
        when (account) {
            is Account.Emailish -> {
                val component = ProfileDetailsComponentImpl(
                    accountManager = accountManager,
                    account = account
                )
                ProfileComponent.Child.LoggedIn(component)
            }
            is Account.Anonymous,
            null -> {
                val component = AuthComponentImpl(accountManager = accountManager)
                ProfileComponent.Child.LoggedOut(component)
            }
        }
}

package com.nikitakrapo.monkeybusiness.profile

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.nikitakrapo.account.Account
import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.monkeybusiness.profile.auth.AuthComponentImpl
import com.nikitakrapo.monkeybusiness.profile.profiledetails.ProfileDetailsComponentImpl
import com.nikitakrapo.navigation.stack.childFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileComponentImpl(
    componentContext: ComponentContext,
    private val onBackClicked: () -> Unit,
    private val accountManager: AccountManager,
) : ProfileComponent, ComponentContext by componentContext {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val navigation = StackNavigation<ProfileScreen>()

    override val child: StateFlow<ProfileComponent.Child> = childFlow(
        source = navigation,
        initialConfiguration = getScreenForAccount(accountManager.currentAccount.value),
        handleBackButton = true,
        childFactory = ::child,
    )

    override fun onBackArrowClicked() {
        onBackClicked()
    }

    init {
        scope.launch {
            accountManager.currentAccount.collect { newAccount ->
                navigation.replaceCurrent(getScreenForAccount(newAccount))
            }
        }
    }

    @Parcelize
    sealed class ProfileScreen : Parcelable {
        class Profile(val account: Account.Emailish) : ProfileScreen()
        object Auth : ProfileScreen()
    }

    private fun getScreenForAccount(account: Account?): ProfileScreen {
        return when (account) {
            is Account.Emailish -> ProfileScreen.Profile(account)
            is Account.Anonymous,
            null -> ProfileScreen.Auth
        }
    }

    private fun child(
        screen: ProfileScreen,
        componentContext: ComponentContext
    ): ProfileComponent.Child =
        when (screen) {
            is ProfileScreen.Profile -> {
                val component = ProfileDetailsComponentImpl(
                    accountManager = accountManager,
                    account = screen.account
                )
                ProfileComponent.Child.LoggedIn(component)
            }
            is ProfileScreen.Auth -> {
                val component = AuthComponentImpl(
                    componentContext = componentContext,
                    accountManager = accountManager
                )
                ProfileComponent.Child.LoggedOut(component)
            }
        }
}

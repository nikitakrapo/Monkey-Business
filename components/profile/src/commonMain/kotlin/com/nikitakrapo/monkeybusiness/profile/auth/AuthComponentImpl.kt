package com.nikitakrapo.monkeybusiness.profile.auth

import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.monkeybusiness.profile.auth.login.LoginComponentImpl
import com.nikitakrapo.monkeybusiness.profile.auth.register.RegistrationComponentImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthComponentImpl(
    initialConfig: Config = Config.Login,
    private val accountManager: AccountManager,
) : AuthComponent {

    private val stateFlow = MutableStateFlow(AuthComponent.State(createChildForConfig(initialConfig)))
    override val state: StateFlow<AuthComponent.State> get() = stateFlow.asStateFlow()

    override fun openLogin() {
        stateFlow.value = AuthComponent.State(createChildForConfig(Config.Login))
    }

    override fun openRegistration() {
        stateFlow.value = AuthComponent.State(createChildForConfig(Config.Registration))
    }

    sealed class Config {
        object Login : Config()
        object Registration : Config()
    }

    private fun createChildForConfig(config: Config): AuthComponent.Child =
        when (config) {
            Config.Login -> {
                val component = LoginComponentImpl(
                    navigateToRegistration = ::openRegistration,
                    accountManager = accountManager,
                )
                AuthComponent.Child.Login(component)
            }
            Config.Registration -> {
                val component = RegistrationComponentImpl(accountManager = accountManager)
                AuthComponent.Child.Registration(component)
            }
        }
}
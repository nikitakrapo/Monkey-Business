package com.nikitakrapo.monkeybusiness.profile.auth

import com.nikitakrapo.monkeybusiness.profile.auth.login.LoginComponent
import com.nikitakrapo.monkeybusiness.profile.auth.register.RegistrationComponent
import kotlinx.coroutines.flow.StateFlow

interface AuthComponent {

    val state: StateFlow<State>

    fun openLogin()
    fun openRegistration()

    data class State(
        val child: Child,
    )

    sealed class Child {
        class Login(val component: LoginComponent) : Child()
        class Registration(val component: RegistrationComponent) : Child()
    }
}
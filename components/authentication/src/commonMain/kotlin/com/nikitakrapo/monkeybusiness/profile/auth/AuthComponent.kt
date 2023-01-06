package com.nikitakrapo.monkeybusiness.profile.auth

import com.nikitakrapo.monkeybusiness.profile.auth.login.LoginComponent
import com.nikitakrapo.monkeybusiness.profile.auth.register.RegistrationComponent
import kotlinx.coroutines.flow.StateFlow

interface AuthComponent {

    val child: StateFlow<Child>

    fun openLogin()
    fun openRegistration()

    sealed class Child {
        class Login(val component: LoginComponent) : Child()
        class Registration(val component: RegistrationComponent) : Child()
    }
}
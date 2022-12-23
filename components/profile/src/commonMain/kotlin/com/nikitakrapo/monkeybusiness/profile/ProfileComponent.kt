package com.nikitakrapo.monkeybusiness.profile

import com.nikitakrapo.monkeybusiness.profile.login.LoginComponent
import com.nikitakrapo.monkeybusiness.profile.profiledetails.ProfileDetailsComponent
import kotlinx.coroutines.flow.StateFlow

interface ProfileComponent {

    val state: StateFlow<State>

    data class State(
        val child: Child,
    )

    sealed class Child {
        class LoggedIn(val component: ProfileDetailsComponent) : Child()
        class LoggedOut(val component: LoginComponent) : Child()
    }
}
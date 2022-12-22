package com.nikitakrapo.monkeybusiness.profile

import com.nikitakrapo.monkeybusiness.profile.login.LoginComponent
import com.nikitakrapo.monkeybusiness.profile.profiledetails.ProfileDetailsComponent
import kotlinx.coroutines.flow.StateFlow

interface ProfileComponent {

    val state: StateFlow<State>

    sealed class State {
        class LoggedIn(
            val profileDetailsComponent: ProfileDetailsComponent,
        ) : State()

        class LoggedOut(
            val loginComponent: LoginComponent,
        ) : State()
    }
}
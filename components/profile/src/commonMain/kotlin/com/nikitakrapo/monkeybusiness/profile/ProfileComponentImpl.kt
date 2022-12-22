package com.nikitakrapo.monkeybusiness.profile

import com.nikitakrapo.monkeybusiness.profile.login.LoginComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileComponentImpl : ProfileComponent {
    override val state: StateFlow<ProfileComponent.State> =
        MutableStateFlow(
            ProfileComponent.State.LoggedOut(
                object : LoginComponent {
                    override fun onLoginClicked() {}
                    override fun onRegisterClicked() {}
                }
            )
        )
}
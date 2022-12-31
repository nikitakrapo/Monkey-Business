package com.nikitakrapo.monkeybusiness.profile

import com.nikitakrapo.monkeybusiness.profile.auth.AuthComponent
import com.nikitakrapo.monkeybusiness.profile.profiledetails.ProfileDetailsComponent
import kotlinx.coroutines.flow.StateFlow

interface ProfileComponent {

    val child: StateFlow<Child>

    fun onBackArrowClicked()

    sealed class Child {
        class LoggedIn(val component: ProfileDetailsComponent) : Child()
        class LoggedOut(val component: AuthComponent) : Child()
    }
}

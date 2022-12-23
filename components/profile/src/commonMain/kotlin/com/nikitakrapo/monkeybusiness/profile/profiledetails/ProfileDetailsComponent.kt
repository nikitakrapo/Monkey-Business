package com.nikitakrapo.monkeybusiness.profile.profiledetails

import kotlinx.coroutines.flow.StateFlow

interface ProfileDetailsComponent {

    val state: StateFlow<State>

    fun onLogoutClicked()

    data class State(
        val email: String,
        val username: String?,
        val profileImageUrl: String?,
    )
}
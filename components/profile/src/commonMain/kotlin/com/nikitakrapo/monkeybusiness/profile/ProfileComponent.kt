package com.nikitakrapo.monkeybusiness.profile

import kotlinx.coroutines.flow.StateFlow

interface ProfileComponent {

    val state: StateFlow<State>

    fun onLogoutClicked()

    data class State(
        val email: String,
        val username: String?,
        val profileImageUrl: String?,
    )
}

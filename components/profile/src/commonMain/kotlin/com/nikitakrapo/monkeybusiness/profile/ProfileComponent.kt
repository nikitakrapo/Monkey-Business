package com.nikitakrapo.monkeybusiness.profile

import kotlinx.coroutines.flow.StateFlow

interface ProfileComponent {

    val state: StateFlow<State>

    fun onEditClicked()
    fun onLogoutClicked()

    data class State(
        val displayName: String,
        val profileImageUrl: String?,
    )
}

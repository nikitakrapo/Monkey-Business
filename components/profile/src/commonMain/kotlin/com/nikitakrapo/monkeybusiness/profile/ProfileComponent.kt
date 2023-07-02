package com.nikitakrapo.monkeybusiness.profile

import kotlinx.coroutines.flow.StateFlow

interface ProfileComponent {

    val state: StateFlow<State>

    fun onEditClicked()
    fun onSettingsClicked()
    fun onLogoutClicked()

    data class State(
        val displayName: String,
        val profileImageUrl: String?,
    )
}

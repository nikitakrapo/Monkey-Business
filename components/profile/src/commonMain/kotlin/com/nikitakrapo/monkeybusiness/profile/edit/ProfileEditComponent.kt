package com.nikitakrapo.monkeybusiness.profile.edit

import kotlinx.coroutines.flow.StateFlow

interface ProfileEditComponent {

    val state: StateFlow<State>

    fun onUsernameTextChanged(text: String)

    fun onNavigateBackClicked()
    fun onSaveChangesClicked()

    data class State(
        val username: String,
        val isLoading: Boolean,
        val error: String?,
    )
}

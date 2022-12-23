package com.nikitakrapo.monkeybusiness.profile.auth.register

import kotlinx.coroutines.flow.StateFlow

interface RegistrationComponent {
    val state: StateFlow<State>

    fun onUsernameTextChanged(text: String)
    fun onEmailTextChanged(text: String)
    fun onPasswordTextChanged(text: String)
    fun onRegisterClicked()

    data class State(
        val username: String,
        val email: String,
        val password: String,
        val isLoading: Boolean,
        val error: String?,
    )
}
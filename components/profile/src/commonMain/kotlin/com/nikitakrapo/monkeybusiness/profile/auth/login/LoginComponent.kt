package com.nikitakrapo.monkeybusiness.profile.auth.login

import kotlinx.coroutines.flow.StateFlow

interface LoginComponent {

    val state: StateFlow<State>

    fun onEmailTextChanged(text: String)
    fun onPasswordTextChanged(text: String)
    fun onLoginClicked()
    fun onRegistrationClicked()

    data class State(
        val emailText: String,
        val passwordText: String,
        val isLoading: Boolean,
        val error: String? = null,
    )
}

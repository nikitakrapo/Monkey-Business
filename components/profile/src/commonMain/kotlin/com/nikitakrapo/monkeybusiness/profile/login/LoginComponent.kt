package com.nikitakrapo.monkeybusiness.profile.login

import kotlinx.coroutines.flow.StateFlow

interface LoginComponent {

    val state: StateFlow<State>

    fun onEmailTextChanged(text: String)
    fun onPasswordTextChanged(text: String)
    fun onLoginClicked()
    fun onRegisterClicked()

    data class State(
        val emailText: String,
        val passwordText: String,
        val isLoading: Boolean,
        val error: String? = null,
    )
}
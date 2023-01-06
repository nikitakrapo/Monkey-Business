package com.nikitakrapo.monkeybusiness.profile.auth.register

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.coroutines.flow.StateFlow

interface RegistrationComponent {
    val state: StateFlow<State>

    fun onUsernameTextChanged(text: String)
    fun onEmailTextChanged(text: String)
    fun onPasswordTextChanged(text: String)
    fun onRegisterClicked()

    @Parcelize
    data class State(
        val username: String,
        val email: String,
        val password: String,
        val isLoading: Boolean,
        val error: String?,
    ) : Parcelable
}

package com.nikitakrapo.monkeybusiness.profile.auth.login

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.coroutines.flow.StateFlow

interface LoginComponent {

    val state: StateFlow<State>

    fun onEmailTextChanged(text: String)
    fun onPasswordTextChanged(text: String)
    fun onLoginClicked()
    fun onRegistrationClicked()

    @Parcelize
    data class State(
        val emailText: String,
        val passwordText: String,
        val isLoading: Boolean,
        val error: String? = null,
    ) : Parcelable
}

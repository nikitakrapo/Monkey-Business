package com.nikitakrapo.monkeybusiness.profile.auth.register

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.mvi.createFeature
import com.nikitakrapo.mvi.feature.FeatureFactory
import com.nikitakrapo.mvi.getValue
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class RegistrationComponentImpl(
    componentContext: ComponentContext,
    featureFactory: FeatureFactory = FeatureFactory(),
    private val accountManager: AccountManager,
) : RegistrationComponent, ComponentContext by componentContext {

    private val feature by createFeature {
        featureFactory.create<Intent, Intent, Effect, RegistrationComponent.State, Nothing>(
            name = "RegistrationFeature",
            initialState = RegistrationComponent.State(
                username = "",
                email = "",
                password = "",
                isLoading = false,
                error = null,
            ),
            intentToAction = { it },
            actor = { action, state ->
                when (action) {
                    is Intent.ChangeUsernameText -> flowOf(Effect.UsernameChanged(action.text))
                    is Intent.ChangeEmailText -> flowOf(Effect.EmailChanged(action.text))
                    is Intent.ChangePasswordText -> flowOf(Effect.PasswordChanged(action.text))
                    Intent.Register -> flow {
                        emit(Effect.StartLoading)
                        accountManager.createAccount(
                            email = state.email,
                            password = state.password,
                            username = state.username,
                        )
                            .fold(
                                onSuccess = {
                                    emit(Effect.FinishLoading(Result.success(Unit)))
                                },
                                onFailure = {
                                    emit(Effect.FinishLoading(Result.failure(it)))
                                },
                            )
                    }
                }
            },
            reducer = {
                when (it) {
                    is Effect.UsernameChanged -> copy(
                        username = it.text,
                        error = null,
                    )
                    is Effect.EmailChanged -> copy(
                        email = it.text,
                        error = null,
                    )
                    is Effect.PasswordChanged -> copy(
                        password = it.text,
                        error = null,
                    )
                    is Effect.FinishLoading -> copy(
                        error = it.result.exceptionOrNull()?.message,
                        isLoading = false,
                    )
                    Effect.StartLoading -> copy(
                        isLoading = true,
                        error = null,
                    )
                }
            },
        )
    }

    override val state: StateFlow<RegistrationComponent.State> get() = feature.state

    override fun onUsernameTextChanged(text: String) {
        feature.accept(Intent.ChangeUsernameText(text))
    }

    override fun onEmailTextChanged(text: String) {
        feature.accept(Intent.ChangeEmailText(text))
    }

    override fun onPasswordTextChanged(text: String) {
        feature.accept(Intent.ChangePasswordText(text))
    }

    override fun onRegisterClicked() {
        feature.accept(Intent.Register)
    }

    private sealed class Intent {
        class ChangeUsernameText(val text: String) : Intent()
        class ChangeEmailText(val text: String) : Intent()
        class ChangePasswordText(val text: String) : Intent()
        object Register : Intent()
    }

    private sealed class Effect {
        class UsernameChanged(val text: String) : Effect()
        class EmailChanged(val text: String) : Effect()
        class PasswordChanged(val text: String) : Effect()
        object StartLoading : Effect()
        class FinishLoading(val result: Result<Unit>) : Effect()
    }
}

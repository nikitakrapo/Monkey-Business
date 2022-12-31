package com.nikitakrapo.monkeybusiness.profile.auth.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.statekeeper.consume
import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.mvi.feature.FeatureFactory
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class LoginComponentImpl(
    componentContext: ComponentContext,
    private val navigateToRegistration: () -> Unit,
    private val accountManager: AccountManager,
    featureFactory: FeatureFactory = FeatureFactory(),
) : LoginComponent, ComponentContext by componentContext {

    init {
        stateKeeper.register(key = STATE_KEY) {
            feature.state.value.copy(
                isLoading = false,
            )
        }
    }

    private var persistedState: LoginComponent.State = stateKeeper.consume(key = STATE_KEY)
        ?: LoginComponent.State(
            emailText = "",
            passwordText = "",
            isLoading = false
        )

    private val feature =
        featureFactory.create<Intent, Intent, Effect, LoginComponent.State, Nothing>(
            name = "LoginFeature",
            initialState = persistedState,
            intentToAction = { it },
            actor = { action, state ->
                when (action) {
                    is Intent.ChangeEmailText -> flowOf(Effect.EmailChanged(action.text))
                    is Intent.ChangePasswordText -> flowOf(Effect.PasswordChanged(action.text))
                    Intent.Login -> flow {
                        emit(Effect.StartLoading)
                        accountManager.login(email = state.emailText, password = state.passwordText)
                            .fold(
                                onSuccess = {
                                    emit(Effect.FinishLoading(Result.success(Unit)))
                                },
                                onFailure = {
                                    emit(Effect.FinishLoading(Result.failure(it)))
                                }
                            )
                    }
                }
            },
            reducer = {
                when (it) {
                    is Effect.EmailChanged -> copy(
                        emailText = it.text,
                        error = null
                    )
                    is Effect.PasswordChanged -> copy(
                        passwordText = it.text,
                        error = null
                    )
                    is Effect.FinishLoading -> copy(
                        error = it.result.exceptionOrNull()?.message,
                        isLoading = false
                    )
                    Effect.StartLoading -> copy(
                        isLoading = true,
                        error = null
                    )
                }
            }
        )

    override val state: StateFlow<LoginComponent.State> get() = feature.state

    override fun onEmailTextChanged(text: String) {
        feature.accept(Intent.ChangeEmailText(text))
    }

    override fun onPasswordTextChanged(text: String) {
        feature.accept(Intent.ChangePasswordText(text))
    }

    override fun onLoginClicked() {
        feature.accept(Intent.Login)
    }

    override fun onRegistrationClicked() {
        navigateToRegistration()
    }

    sealed class Intent {
        class ChangeEmailText(val text: String) : Intent()
        class ChangePasswordText(val text: String) : Intent()
        object Login : Intent()
    }

    sealed class Effect {
        class EmailChanged(val text: String) : Effect()
        class PasswordChanged(val text: String) : Effect()
        object StartLoading : Effect()
        class FinishLoading(val result: Result<Unit>) : Effect()
    }

    companion object {
        private const val STATE_KEY = "LoginState"
    }
}

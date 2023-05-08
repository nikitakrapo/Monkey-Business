package com.nikitakrapo.monkeybusiness.profile.edit

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.decompose.coroutines.coroutineScope
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditComponent.State
import com.nikitakrapo.mvi.feature.FeatureFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class ProfileEditComponentImpl(
    componentContext: ComponentContext,
    private val dependencies: ProfileEditDependencies,
    private val closeProfileEdit: () -> Unit,
    featureFactory: FeatureFactory = FeatureFactory(),
) : ProfileEditComponent, ComponentContext by componentContext {

    private val scope = coroutineScope(Dispatchers.Main)

    private val accountManager get() = dependencies.accountManager

    init {
        scope.launch {
            feature.events.collect { event ->
                when (event) {
                    Event.SavingFinishedSuccessfully -> closeProfileEdit()
                }
            }
        }
    }

    override val state: StateFlow<State> get() = feature.state

    private val feature = featureFactory.create<Intent, Intent, Effect, State, Event>(
        name = "ProfileEdit",
        initialState = State(
            username = "",
            isLoading = false,
            error = null,
        ),
        intentToAction = { it },
        reducer = { effect ->
            when (effect) {
                is Effect.UsernameTextChanged -> copy(
                    username = effect.text,
                    error = null,
                )
                Effect.SavingStarted -> copy(isLoading = true)
                is Effect.SavingFinished -> copy(
                    isLoading = false,
                    error = effect.result.exceptionOrNull()?.message,
                )
            }
        },
        actor = { action, state ->
            when (action) {
                is Intent.ChangeUsernameText -> flowOf(Effect.UsernameTextChanged(action.text))
                Intent.SaveChanges -> flow {
                    emit(Effect.SavingStarted)
                    val result = accountManager.updateAccount {
                        username = state.username
                    }
                    emit(Effect.SavingFinished(result))
                }
            }
        },
        eventsPublisher = { _, effect, _ ->
            if (effect is Effect.SavingFinished && effect.result.isSuccess) {
                Event.SavingFinishedSuccessfully
            } else {
                null
            }
        },
    )

    override fun onUsernameTextChanged(text: String) {
        feature.accept(Intent.ChangeUsernameText(text))
    }

    override fun onNavigateBackClicked() {
        closeProfileEdit()
    }

    override fun onSaveChangesClicked() {
        feature.accept(Intent.SaveChanges)
    }

    private sealed class Intent {
        class ChangeUsernameText(val text: String) : Intent()
        object SaveChanges : Intent()
    }

    private sealed class Effect {
        class UsernameTextChanged(val text: String) : Effect()
        object SavingStarted : Effect()
        class SavingFinished(val result: Result<Unit>) : Effect()
    }

    private sealed class Event {
        object SavingFinishedSuccessfully : Event()
    }
}

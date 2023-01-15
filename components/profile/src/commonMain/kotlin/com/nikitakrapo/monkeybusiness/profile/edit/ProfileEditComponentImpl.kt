package com.nikitakrapo.monkeybusiness.profile.edit

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileEditComponentImpl(
    componentContext: ComponentContext,
    private val navigateBack: () -> Unit,
) : ProfileEditComponent, ComponentContext by componentContext {

    private val stateFlow = MutableStateFlow(
        ProfileEditComponent.State(
            username = "",
            isLoading = false,
        )
    )
    override val state: StateFlow<ProfileEditComponent.State> get() = stateFlow.asStateFlow()

    override fun onUsernameTextChanged(text: String) {
        stateFlow.value = stateFlow.value.copy(username = text)
    }

    override fun onNavigateBackClicked() {
        navigateBack()
    }

    override fun onSaveChangesClicked() {
    }
}
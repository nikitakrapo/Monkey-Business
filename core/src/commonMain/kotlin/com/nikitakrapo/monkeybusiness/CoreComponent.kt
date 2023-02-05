package com.nikitakrapo.monkeybusiness

import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.monkeybusiness.finances.transactions.TransactionAddComponent
import com.nikitakrapo.monkeybusiness.home.HomeComponent
import com.nikitakrapo.monkeybusiness.profile.auth.AuthComponent
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditComponent
import kotlinx.coroutines.flow.StateFlow

interface CoreComponent {

    val state: StateFlow<State>

    val childStack: StateFlow<ChildStack<*, Child>>
    val modalChildStack: StateFlow<ChildStack<*, ModalChild>>

    fun dismissModal()

    sealed class Child {
        class Home(val component: HomeComponent) : Child()
        class Authentication(val component: AuthComponent) : Child()
    }

    sealed class ModalChild {
        object None : ModalChild()
        class TransactionAdd(val component: TransactionAddComponent) : ModalChild()
        class ProfileEdit(val component: ProfileEditComponent) : ModalChild()
    }

    data class State(
        val isModalDismissing: Boolean,
    )
}

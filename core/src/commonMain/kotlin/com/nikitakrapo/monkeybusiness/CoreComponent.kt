package com.nikitakrapo.monkeybusiness

import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.monkeybusiness.debug.DebugPanelComponent
import com.nikitakrapo.monkeybusiness.finances.accounts.opening.BankAccountOpeningComponent
import com.nikitakrapo.monkeybusiness.finances.products.ProductOpeningComponent
import com.nikitakrapo.monkeybusiness.home.HomeComponent
import com.nikitakrapo.monkeybusiness.profile.auth.AuthComponent
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditComponent
import kotlinx.coroutines.flow.StateFlow

interface CoreComponent {

    val state: StateFlow<State>

    val debugPanelComponent: DebugPanelComponent

    val childStack: StateFlow<ChildStack<*, Child>>
    val modalChildStack: StateFlow<ChildStack<*, ModalChild>>

    fun onModalDismissed()

    sealed class Child {
        class Home(val component: HomeComponent) : Child()
        class Settings(val component: Unit) : Child()
        class BankAccountOpening(val component: BankAccountOpeningComponent) : Child()
        class Authentication(val component: AuthComponent) : Child()
    }

    sealed class ModalChild {
        object None : ModalChild()
        class ProfileEdit(val component: ProfileEditComponent) : ModalChild()
        class ProductOpening(val component: ProductOpeningComponent) : ModalChild()
    }

    data class State(
        val isModalDismissing: Boolean,
    )
}

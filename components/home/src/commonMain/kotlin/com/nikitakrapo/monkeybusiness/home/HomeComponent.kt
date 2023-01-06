package com.nikitakrapo.monkeybusiness.home

import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.monkeybusiness.finances.FinancesComponent
import com.nikitakrapo.monkeybusiness.profile.ProfileComponent
import kotlinx.coroutines.flow.StateFlow

interface HomeComponent {

    val childStack: StateFlow<ChildStack<HomeComponentImpl.HomeScreen, Child>>

    fun onFinancesClicked()
    fun onProfileClicked()

    sealed class Child {
        class Finances(val component: FinancesComponent) : Child()
        class Profile(val component: ProfileComponent) : Child()
    }
}

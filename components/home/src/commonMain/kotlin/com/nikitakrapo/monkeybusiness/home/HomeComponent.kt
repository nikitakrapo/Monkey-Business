package com.nikitakrapo.monkeybusiness.home

import com.nikitakrapo.monkeybusiness.finances.FinancesComponent
import com.nikitakrapo.monkeybusiness.profile.ProfileComponent
import kotlinx.coroutines.flow.StateFlow

interface HomeComponent {

    val child: StateFlow<Child>

    fun onFinancesClicked()
    fun onProfileClicked()

    sealed class Child {
        class Finances(val component: FinancesComponent) : Child()
        class Profile(val component: ProfileComponent) : Child()
    }
}

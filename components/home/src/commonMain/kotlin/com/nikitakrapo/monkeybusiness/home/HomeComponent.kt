package com.nikitakrapo.monkeybusiness.home

import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.monkeybusiness.analytics.AnalyticsComponent
import com.nikitakrapo.monkeybusiness.finances.FinancesComponent
import com.nikitakrapo.monkeybusiness.profile.ProfileComponent
import kotlinx.coroutines.flow.StateFlow

interface HomeComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    fun onFinancesClicked()
    fun onAnalyticsClicked()
    fun onProfileClicked()

    sealed class Child {
        class Finances(val component: FinancesComponent) : Child()
        class Analytics(val component: AnalyticsComponent) : Child()
        class Profile(val component: ProfileComponent) : Child()
    }
}

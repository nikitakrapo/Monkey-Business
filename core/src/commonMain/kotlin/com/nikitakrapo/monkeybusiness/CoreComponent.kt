package com.nikitakrapo.monkeybusiness

import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.monkeybusiness.home.HomeComponent
import com.nikitakrapo.monkeybusiness.profile.ProfileComponent
import kotlinx.coroutines.flow.StateFlow

interface CoreComponent {

    val childStack: StateFlow<ChildStack<CoreComponentImpl.CoreScreen, Child>>

    fun onHomeClicked()
    fun onMoreClicked()

    sealed class Child {
        class Home(val component: HomeComponent) : Child()
        class More(val component: Unit) : Child()
        class Profile(val component: ProfileComponent) : Child()
    }
}

package com.nikitakrapo.monkeybusiness

import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.monkeybusiness.home.HomeComponent
import com.nikitakrapo.monkeybusiness.profile.auth.AuthComponent
import kotlinx.coroutines.flow.StateFlow

interface CoreComponent {

    val childStack: StateFlow<ChildStack<CoreComponentImpl.CoreScreen, Child>>

    sealed class Child {
        class Home(val component: HomeComponent) : Child()
        class Authentication(val component: AuthComponent) : Child()
    }
}

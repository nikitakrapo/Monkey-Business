package com.nikitakrapo.monkeybusiness

import com.nikitakrapo.monkeybusiness.home.HomeComponent
import kotlinx.coroutines.flow.StateFlow

interface CoreComponent {

    val child: StateFlow<Child>

    fun onHomeClicked()
    fun onProfileClicked()

    sealed class Child {
        class Home(val component: HomeComponent) : Child()
        class Profile(val component: Unit) : Child()
    }
}
package com.nikitakrapo.monkeybusiness

import com.nikitakrapo.monkeybusiness.home.HomeComponent
import com.nikitakrapo.monkeybusiness.profile.ProfileComponent
import kotlinx.coroutines.flow.StateFlow

interface CoreComponent {

    val child: StateFlow<Child>

    fun onHomeClicked()
    fun onMoreClicked()

    sealed class Child {
        class Home(val component: HomeComponent) : Child()
        class More(val component: Unit) : Child()
        class Profile(val component: ProfileComponent) : Child()
    }
}

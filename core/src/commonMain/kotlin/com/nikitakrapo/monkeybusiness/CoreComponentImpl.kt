package com.nikitakrapo.monkeybusiness

import com.nikitakrapo.monkeybusiness.home.HomeComponent
import com.nikitakrapo.monkeybusiness.home.HomeComponentImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CoreComponentImpl(
    initialScreen: CoreScreen = CoreScreen.HOME,
) : CoreComponent {

    private val homeComponent: HomeComponent = HomeComponentImpl()
    private val profileComponent = Unit

    private val childFlow = MutableStateFlow(createChildForScreen(initialScreen))
    override val child: StateFlow<CoreComponent.Child> = childFlow.asStateFlow()

    override fun onHomeClicked() {
        childFlow.value = createChildForScreen(CoreScreen.HOME)
    }

    override fun onProfileClicked() {
        childFlow.value = createChildForScreen(CoreScreen.PROFILE)
    }

    enum class CoreScreen {
        HOME,
        PROFILE
    }

    private fun createChildForScreen(screen: CoreScreen) = when (screen) {
        CoreScreen.HOME -> CoreComponent.Child.Home(homeComponent)
        CoreScreen.PROFILE -> CoreComponent.Child.Profile(profileComponent)
    }
}
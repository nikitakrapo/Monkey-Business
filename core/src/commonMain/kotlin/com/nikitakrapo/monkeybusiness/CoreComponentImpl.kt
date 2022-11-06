package com.nikitakrapo.monkeybusiness

import com.nikitakrapo.component.ComponentContext
import com.nikitakrapo.monkeybusiness.home.HomeComponent
import com.nikitakrapo.monkeybusiness.home.HomeComponentImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CoreComponentImpl(
    componentContext: ComponentContext,
    initialScreen: CoreScreen = CoreScreen.HOME,
) : CoreComponent, ComponentContext by componentContext {

    private val homeComponent: HomeComponent = HomeComponentImpl(
        navigateToSearch = {},
        navigateToProfile = ::navigateToProfile,
    )
    private val moreComponent = Unit
    private val profileComponent = Unit

    private val childFlow = MutableStateFlow(createChildForScreen(initialScreen))
    override val child: StateFlow<CoreComponent.Child> = childFlow.asStateFlow()

    override fun onHomeClicked() {
        childFlow.value = createChildForScreen(CoreScreen.HOME)
    }

    override fun onMoreClicked() {
        childFlow.value = createChildForScreen(CoreScreen.MORE)
    }

    private fun navigateToProfile() {
        childFlow.value = createChildForScreen(CoreScreen.PROFILE)
    }

    enum class CoreScreen {
        HOME,
        MORE,
        PROFILE
    }

    private fun createChildForScreen(screen: CoreScreen) = when (screen) {
        CoreScreen.HOME -> CoreComponent.Child.Home(homeComponent)
        CoreScreen.MORE -> CoreComponent.Child.More(moreComponent)
        CoreScreen.PROFILE -> CoreComponent.Child.Profile(profileComponent)
    }
}
package com.nikitakrapo.monkeybusiness

import com.nikitakrapo.account.FirebaseAccountManager
import com.nikitakrapo.component.ComponentContext
import com.nikitakrapo.monkeybusiness.home.HomeComponentImpl
import com.nikitakrapo.monkeybusiness.profile.ProfileComponentImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CoreComponentImpl(
    componentContext: ComponentContext,
    initialScreen: CoreScreen = CoreScreen.Home,
    private val analytics: CoreScreenAnalytics
) : CoreComponent, ComponentContext by componentContext {

    private val accountManager = FirebaseAccountManager()

    private val childFlow = MutableStateFlow(createChildForScreen(initialScreen))
    override val child: StateFlow<CoreComponent.Child> get() = childFlow.asStateFlow()

    override fun onHomeClicked() {
        childFlow.value = createChildForScreen(CoreScreen.Home)
        analytics.onHomeClicked()
    }

    override fun onMoreClicked() {
        childFlow.value = createChildForScreen(CoreScreen.More)
        analytics.onMoreClicked()
    }

    private fun navigateToProfile() {
        childFlow.value = createChildForScreen(CoreScreen.Profile)
    }

    sealed class CoreScreen {
        object Home : CoreScreen()
        object More : CoreScreen()
        object Profile : CoreScreen()
    }

    private fun createChildForScreen(screen: CoreScreen) = when (screen) {
        CoreScreen.Home -> CoreComponent.Child.Home(
            HomeComponentImpl(
                navigateToSearch = {},
                navigateToProfile = ::navigateToProfile
            )
        )
        CoreScreen.More -> CoreComponent.Child.More(Unit)
        CoreScreen.Profile -> CoreComponent.Child.Profile(
            ProfileComponentImpl(accountManager)
        )
    }
}

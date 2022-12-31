package com.nikitakrapo.monkeybusiness

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.monkeybusiness.home.HomeComponentImpl
import com.nikitakrapo.monkeybusiness.profile.ProfileComponentImpl
import com.nikitakrapo.navigation.stack.childFlow
import kotlinx.coroutines.flow.StateFlow

class CoreComponentImpl(
    componentContext: ComponentContext,
    initialScreen: CoreScreen = CoreScreen.Home,
    private val analytics: CoreScreenAnalytics,
    private val accountManager: AccountManager,
) : CoreComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<CoreScreen>()

    override val child: StateFlow<CoreComponent.Child> = childFlow(
        source = navigation,
        initialConfiguration = initialScreen,
        handleBackButton = true,
        childFactory = ::createChildForScreen,
    )

    override fun onHomeClicked() {
        navigation.bringToFront(CoreScreen.Home)
        analytics.onHomeClicked()
    }

    override fun onMoreClicked() {
        navigation.bringToFront(CoreScreen.More)
        analytics.onMoreClicked()
    }

    private fun navigateToProfile() {
        navigation.bringToFront(CoreScreen.Profile)
    }

    @Parcelize
    sealed class CoreScreen : Parcelable {
        object Home : CoreScreen()
        object More : CoreScreen()
        object Profile : CoreScreen()
    }

    private fun createChildForScreen(screen: CoreScreen, componentContext: ComponentContext) = when (screen) {
        CoreScreen.Home -> CoreComponent.Child.Home(
            HomeComponentImpl(
                navigateToSearch = {},
                navigateToProfile = ::navigateToProfile
            )
        )
        CoreScreen.More -> CoreComponent.Child.More(Unit)
        CoreScreen.Profile -> CoreComponent.Child.Profile(
            ProfileComponentImpl(
                onBackClicked = navigation::pop,
                accountManager = accountManager
            )
        )
    }
}

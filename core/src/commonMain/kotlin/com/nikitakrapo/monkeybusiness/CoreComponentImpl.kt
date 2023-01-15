package com.nikitakrapo.monkeybusiness

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.nikitakrapo.account.currentAccount
import com.nikitakrapo.account.models.Account
import com.nikitakrapo.decompose.coroutines.coroutineScope
import com.nikitakrapo.monkeybusiness.home.HomeComponentImpl
import com.nikitakrapo.monkeybusiness.profile.auth.AuthComponentImpl
import com.nikitakrapo.navigation.stack.childStackFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoreComponentImpl(
    componentContext: ComponentContext,
    private val dependencies: CoreDependencies
) : CoreComponent, ComponentContext by componentContext {

    private val scope = coroutineScope(Dispatchers.Main)

    init {
        scope.launch {
            dependencies.accountManager.account.collect { account ->
                navigateAuthIfNeeded(account)
            }
        }
    }

    private val analytics = CoreScreenAnalytics(dependencies.analyticsManager)

    private val navigation = StackNavigation<CoreScreen>()

    override val childStack: StateFlow<ChildStack<CoreScreen, CoreComponent.Child>> =
        childStackFlow(
            source = navigation,
            key = "CoreChildStack",
            initialConfiguration = getScreenForAuthState(
                dependencies.accountManager.currentAccount
            ),
            handleBackButton = true,
            childFactory = ::createChild,
        )

    @Parcelize
    sealed class CoreScreen : Parcelable {
        object Home : CoreScreen()
        object Authentication : CoreScreen()
    }

    private val modalNavigation = StackNavigation<CoreModalScreen>()

    override val modalChildStack: StateFlow<ChildStack<*, CoreComponent.ModalChild>> =
        childStackFlow(
            source = modalNavigation,
            key = "CoreModalChildStack",
            initialConfiguration = CoreModalScreen.None,
            handleBackButton = true,
            childFactory = ::createModalChild
        )

    @Parcelize
    sealed class CoreModalScreen : Parcelable {
        object None : CoreModalScreen()
        object ProfileEdit : CoreModalScreen()
    }

    private fun createChild(
        screen: CoreScreen,
        componentContext: ComponentContext
    ): CoreComponent.Child = when (screen) {
        CoreScreen.Home -> {
            analytics.onHomeShown()
            CoreComponent.Child.Home(
                HomeComponentImpl(
                    componentContext = componentContext,
                    dependencies = dependencies.homeDependencies()
                )
            )
        }
        CoreScreen.Authentication -> {
            analytics.onAuthenticationShown()
            CoreComponent.Child.Authentication(
                AuthComponentImpl(
                    componentContext = componentContext,
                    dependencies = dependencies.authDependencies(),
                )
            )
        }
    }

    private fun navigateAuthIfNeeded(account: Account?) {
        val currentScreen = childStack.value.active.configuration
        val expectedScreen = getScreenForAuthState(account)
        if (currentScreen != expectedScreen) {
            navigation.replaceAll(expectedScreen)
        }
    }

    private fun getScreenForAuthState(account: Account?): CoreScreen {
        return if (account == null) CoreScreen.Authentication else CoreScreen.Home
    }

    private fun createModalChild(
        screen: CoreModalScreen,
        componentContext: ComponentContext
    ): CoreComponent.ModalChild = when (screen) {
        CoreModalScreen.None -> CoreComponent.ModalChild.None
        CoreModalScreen.ProfileEdit -> CoreComponent.ModalChild.ProfileEdit(Unit)
    }
}

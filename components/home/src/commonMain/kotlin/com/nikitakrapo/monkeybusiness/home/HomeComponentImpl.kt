package com.nikitakrapo.monkeybusiness.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.nikitakrapo.account.currentAccount
import com.nikitakrapo.account.models.Account
import com.nikitakrapo.monkeybusiness.finances.FinancesComponentImpl
import com.nikitakrapo.monkeybusiness.profile.ProfileComponentImpl
import com.nikitakrapo.navigation.stack.childStackFlow
import kotlinx.coroutines.flow.StateFlow

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val dependencies: HomeDependencies
) : HomeComponent, ComponentContext by componentContext {

    private val analytics = HomeScreenAnalytics(dependencies.analyticsManager)

    private val navigation = StackNavigation<HomeScreen>()

    override val childStack: StateFlow<ChildStack<HomeScreen, HomeComponent.Child>> =
        childStackFlow(
            source = navigation,
            initialConfiguration = HomeScreen.Finances,
            handleBackButton = true,
            childFactory = ::createChild,
        )

    override fun onFinancesClicked() {
        analytics.onFinancesClicked()
        navigation.bringToFront(HomeScreen.Finances)
    }

    override fun onProfileClicked() {
        analytics.onProfileClicked()
        val currentAccount = dependencies.accountManager.currentAccount
        (currentAccount as? Account.Emailish)?.let { account ->
            navigation.bringToFront(HomeScreen.Profile(account))
        } ?: run { /* TODO: log error */ }
    }

    @Parcelize
    sealed class HomeScreen : Parcelable {
        object Finances : HomeScreen()

        @Parcelize
        data class Profile(
            val account: Account.Emailish
        ) : HomeScreen(), Parcelable
    }

    private fun createChild(
        screen: HomeScreen,
        componentContext: ComponentContext,
    ): HomeComponent.Child = when (screen) {
        HomeScreen.Finances -> HomeComponent.Child.Finances(
            FinancesComponentImpl(
                componentContext = componentContext,
            )
        )
        is HomeScreen.Profile -> HomeComponent.Child.Profile(
            ProfileComponentImpl(
                componentContext = componentContext,
                account = screen.account,
                dependencies = dependencies.profileDependencies(),
            )
        )
    }
}

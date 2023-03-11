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
import com.nikitakrapo.monkeybusiness.finances.opening.ProductOpeningComponentImpl
import com.nikitakrapo.monkeybusiness.finances.opening.ProductOpeningRouter
import com.nikitakrapo.monkeybusiness.finances.transactions.TransactionAddComponentImpl
import com.nikitakrapo.monkeybusiness.home.HomeComponentImpl
import com.nikitakrapo.monkeybusiness.profile.auth.AuthComponentImpl
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditComponentImpl
import com.nikitakrapo.navigation.stack.childStackFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoreComponentImpl(
    componentContext: ComponentContext,
    private val dependencies: CoreDependencies,
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

    private val stateFlow = MutableStateFlow(
        CoreComponent.State(
            isModalDismissing = false
        )
    )
    override val state: StateFlow<CoreComponent.State> get() = stateFlow.asStateFlow()

    private val navigation = StackNavigation<CoreScreen>()

    override val childStack: StateFlow<ChildStack<CoreScreen, CoreComponent.Child>> =
        childStackFlow(
            source = navigation,
            key = "CoreChildStack",
            initialConfiguration = getScreenForAuthState(
                dependencies.accountManager.currentAccount,
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
            childFactory = ::createModalChild,
        )

    private val modalCloseBackCallback = BackCallback(
        isEnabled = modalChildStack.value.backStack.isNotEmpty(),
        onBack = ::dismissModalWithAnimation
    )

    init {
        scope.launch {
            modalChildStack.collect { modalStack ->
                modalCloseBackCallback.isEnabled = modalStack.backStack.isNotEmpty()
            }
        }
        backHandler.register(modalCloseBackCallback)
    }

    @Parcelize
    sealed class CoreModalScreen : Parcelable {
        object None : CoreModalScreen()
        object TransactionAdd : CoreModalScreen()
        object ProfileEdit : CoreModalScreen()
        object ProductOpening : CoreModalScreen()
    }

    private fun dismissModalWithAnimation() {
        stateFlow.value = stateFlow.value.copy(isModalDismissing = true)
    }

    override fun dismissModalInstantly() {
        stateFlow.value = stateFlow.value.copy(isModalDismissing = false)
        modalNavigation.pop()
    }

    private fun createChild(
        screen: CoreScreen,
        componentContext: ComponentContext,
    ): CoreComponent.Child = when (screen) {
        CoreScreen.Home -> {
            analytics.onHomeShown()
            CoreComponent.Child.Home(
                HomeComponentImpl(
                    componentContext = componentContext,
                    dependencies = dependencies.homeDependencies(
                        productOpeningLandingRouter = {
                            modalNavigation.bringToFront(CoreModalScreen.ProductOpening)
                        },
                        profileEditRouter = {
                            modalNavigation.bringToFront(CoreModalScreen.ProfileEdit)
                        },
                    ),
                ),
            )
        }
        CoreScreen.Authentication -> {
            analytics.onAuthenticationShown()
            CoreComponent.Child.Authentication(
                AuthComponentImpl(
                    componentContext = componentContext,
                    dependencies = dependencies.authDependencies(),
                ),
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
        componentContext: ComponentContext,
    ): CoreComponent.ModalChild = when (screen) {
        CoreModalScreen.None -> CoreComponent.ModalChild.None
        CoreModalScreen.TransactionAdd -> CoreComponent.ModalChild.TransactionAdd(
            component = TransactionAddComponentImpl(
                componentContext = componentContext,
                dependencies = dependencies.transactionAddDependencies(),
                closeTransactionAdd = ::dismissModalWithAnimation
            )
        )
        CoreModalScreen.ProfileEdit -> CoreComponent.ModalChild.ProfileEdit(
            component = ProfileEditComponentImpl(
                componentContext = componentContext,
                dependencies = dependencies.profileEditDependencies(),
                closeProfileEdit = ::dismissModalWithAnimation,
            ),
        )
        CoreModalScreen.ProductOpening -> CoreComponent.ModalChild.ProductOpening(
            component = ProductOpeningComponentImpl(
                componentContext = componentContext,
                dependencies = dependencies.productOpeningDependencies(
                    productOpeningRouter = productOpeningRouter()
                )
            )
        )
    }

    private fun productOpeningRouter() = object : ProductOpeningRouter {
        override fun openCardOpening() {
            TODO("Not yet implemented")
        }

        override fun openAccountOpening() {
            TODO("Not yet implemented")
        }
    }
}

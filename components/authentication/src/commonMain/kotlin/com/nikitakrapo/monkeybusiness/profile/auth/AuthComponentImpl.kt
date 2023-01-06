package com.nikitakrapo.monkeybusiness.profile.auth

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.nikitakrapo.monkeybusiness.profile.auth.login.LoginComponentImpl
import com.nikitakrapo.monkeybusiness.profile.auth.register.RegistrationComponentImpl
import com.nikitakrapo.navigation.stack.childFlow
import kotlinx.coroutines.flow.StateFlow

class AuthComponentImpl(
    componentContext: ComponentContext,
    initialScreen: AuthScreen = AuthScreen.Login,
    private val dependencies: AuthDependencies,
) : AuthComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<AuthScreen>()

    override val child: StateFlow<AuthComponent.Child> = childFlow(
        source = navigation,
        initialConfiguration = initialScreen,
        handleBackButton = true,
        childFactory = ::child,
    )

    override fun openLogin() {
        navigation.bringToFront(AuthScreen.Login)
    }

    override fun openRegistration() {
        navigation.bringToFront(AuthScreen.Registration)
    }

    @Parcelize
    sealed class AuthScreen : Parcelable {
        object Login : AuthScreen()
        object Registration : AuthScreen()
    }

    private fun child(
        screen: AuthScreen,
        componentContext: ComponentContext
    ): AuthComponent.Child =
        when (screen) {
            AuthScreen.Login -> {
                val component = LoginComponentImpl(
                    componentContext = componentContext,
                    navigateToRegistration = ::openRegistration,
                    accountManager = dependencies.accountManager,
                )
                AuthComponent.Child.Login(component)
            }
            AuthScreen.Registration -> {
                val component = RegistrationComponentImpl(
                    componentContext = componentContext,
                    accountManager = dependencies.accountManager
                )
                AuthComponent.Child.Registration(component)
            }
        }
}

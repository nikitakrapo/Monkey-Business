package com.nikitakrapo.monkeybusiness.profile.auth

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.nikitakrapo.monkeybusiness.profile.auth.login.LoginComponentImpl
import com.nikitakrapo.monkeybusiness.profile.auth.register.RegistrationComponentImpl
import com.nikitakrapo.navigation.stack.childStackFlow
import kotlinx.coroutines.flow.StateFlow

class AuthComponentImpl(
    componentContext: ComponentContext,
    initialScreen: AuthScreen = AuthScreen.Login,
    private val dependencies: AuthDependencies,
) : AuthComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<AuthScreen>()

    override val childStack: StateFlow<ChildStack<*, AuthComponent.Child>> = childStackFlow(
        source = navigation,
        initialConfiguration = initialScreen,
        handleBackButton = true,
        childFactory = ::child,
    )

    override fun openLogin() {
        navigation.bringToFront(AuthScreen.Login)
    }

    override fun openRegistration(initialEmail: String?) {
        navigation.bringToFront(AuthScreen.Registration(initialEmail = initialEmail))
    }

    @Parcelize
    sealed class AuthScreen : Parcelable {
        object Login : AuthScreen()
        class Registration(val initialEmail: String?) : AuthScreen()
    }

    private fun child(
        screen: AuthScreen,
        componentContext: ComponentContext,
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
            is AuthScreen.Registration -> {
                val component = RegistrationComponentImpl(
                    componentContext = componentContext,
                    initialEmail = screen.initialEmail,
                    accountManager = dependencies.accountManager,
                )
                AuthComponent.Child.Registration(component)
            }
        }
}

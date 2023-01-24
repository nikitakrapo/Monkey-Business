package com.nikitakrapo.monkeybusiness.profile.auth

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.monkeybusiness.profile.auth.login.LoginScreen
import com.nikitakrapo.monkeybusiness.profile.auth.login.PreviewLoginComponent
import com.nikitakrapo.monkeybusiness.profile.auth.registration.RegistrationScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    component: AuthComponent,
) {
    val childStack by component.childStack.collectAsState()

    Children(
        stack = childStack,
        modifier = modifier,
        animation = stackAnimation(slide())
    ) { createdChild ->
        when (val child = createdChild.instance) {
            is AuthComponent.Child.Login -> LoginScreen(
                component = child.component
            )
            is AuthComponent.Child.Registration -> RegistrationScreen(
                component = child.component
            )
        }
    }
}

fun PreviewAuthComponent() = object : AuthComponent {
    override val childStack: StateFlow<ChildStack<*, AuthComponent.Child>>
        get() = MutableStateFlow(
            ChildStack(
                configuration = AuthComponentImpl.AuthScreen.Login,
                instance = AuthComponent.Child.Login(PreviewLoginComponent())
            )
        )

    override fun openLogin() {}
    override fun openRegistration() {}
}

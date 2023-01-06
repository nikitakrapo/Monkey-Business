package com.nikitakrapo.monkeybusiness.profile.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.nikitakrapo.monkeybusiness.profile.auth.login.LoginScreen
import com.nikitakrapo.monkeybusiness.profile.auth.login.PreviewLoginComponent
import com.nikitakrapo.monkeybusiness.profile.auth.registration.RegistrationScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    component: AuthComponent,
) {
    val state by component.child.collectAsState()

    when (val child = state) {
        is AuthComponent.Child.Login -> LoginScreen(
            modifier = modifier,
            component = child.component
        )
        is AuthComponent.Child.Registration -> RegistrationScreen(
            modifier = modifier,
            component = child.component
        )
    }
}

fun PreviewAuthComponent() = object : AuthComponent {
    override val child: StateFlow<AuthComponent.Child>
        get() = MutableStateFlow(AuthComponent.Child.Login(PreviewLoginComponent()))

    override fun openLogin() {}
    override fun openRegistration() {}
}

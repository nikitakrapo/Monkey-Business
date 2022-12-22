package com.nikitakrapo.monkeybusiness.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.nikitakrapo.monkeybusiness.design.components.TopNavigationBar
import com.nikitakrapo.monkeybusiness.profile.login.LoginScreen
import com.nikitakrapo.monkeybusiness.profile.login.PreviewLoginComponent
import com.nikitakrapo.monkeybusiness.profile.profiledetails.ProfileDetailsScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    component: ProfileComponent,
    onBackPressed: () -> Unit,
) {
    val state by component.state.collectAsState()

    Column(modifier = modifier) {
        TopNavigationBar(
            navigationAction = onBackPressed
        )

        when (val currentState = state) {
            is ProfileComponent.State.LoggedIn -> ProfileDetailsScreen(
                modifier = modifier
            )
            is ProfileComponent.State.LoggedOut -> LoginScreen(
                modifier = modifier,
                component = currentState.loginComponent
            )
        }
    }
}

fun PreviewProfileComponent() = object : ProfileComponent {
    override val state: StateFlow<ProfileComponent.State> =
        MutableStateFlow(ProfileComponent.State.LoggedOut(PreviewLoginComponent()))
}
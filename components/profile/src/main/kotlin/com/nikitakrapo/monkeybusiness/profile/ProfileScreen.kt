package com.nikitakrapo.monkeybusiness.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.nikitakrapo.monkeybusiness.design.components.TopNavigationBar
import com.nikitakrapo.monkeybusiness.profile.auth.AuthScreen
import com.nikitakrapo.monkeybusiness.profile.auth.PreviewAuthComponent
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

        when (val child = state.child) {
            is ProfileComponent.Child.LoggedIn -> ProfileDetailsScreen(
                modifier = modifier,
                component = child.component
            )
            is ProfileComponent.Child.LoggedOut -> AuthScreen(
                modifier = modifier,
                component = child.component
            )
        }
    }
}

fun PreviewProfileComponent() = object : ProfileComponent {
    override val state: StateFlow<ProfileComponent.State> =
        MutableStateFlow(ProfileComponent.State(ProfileComponent.Child.LoggedOut(
            PreviewAuthComponent()
        )))
}
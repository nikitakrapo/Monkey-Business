package com.nikitakrapo.monkeybusiness.profile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    component: ProfileComponent,
) {
    val state by component.state.collectAsState()

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 12.dp, start = 12.dp, end = 12.dp)
    ) {
        item {
            ProfileHeader(
                modifier = Modifier
                    .fillMaxWidth(),
                emailText = state.email,
                usernameText = state.username,
                profileImageUrl = state.profileImageUrl
            )
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            FilledTonalButton(onClick = component::onLogoutClicked) {
                Icon(
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text("Logout")
            }
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720
)
@Composable
fun ProfileDetailsScreen_Preview_Username() {
    MonkeyTheme {
        Surface {
            ProfileScreen(
                component = PreviewProfileComponent(
                    email = "nikitakrapo"
                )
            )
        }
    }
}

fun PreviewProfileComponent(
    email: String = "",
    username: String? = null,
) = object : ProfileComponent {
    override val state: StateFlow<ProfileComponent.State>
        get() = MutableStateFlow(
            ProfileComponent.State(
                email,
                username,
                null
            )
        )

    override fun onLogoutClicked() {}
}

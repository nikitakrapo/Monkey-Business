package com.nikitakrapo.monkeybusiness.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

    Column(
        modifier = modifier
            .padding(12.dp),
    ) {
        ProfileHeader(
            modifier = Modifier
                .fillMaxWidth(),
            displayName = state.displayName,
            profileImageUrl = state.profileImageUrl,
            onEditClick = component::onEditClicked,
        )
        Spacer(modifier = Modifier.height(12.dp))
        FilledTonalButton(onClick = component::onLogoutClicked) {
            Icon(
                modifier = Modifier.size(ButtonDefaults.IconSize),
                imageVector = Icons.Default.ExitToApp,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text("Logout")
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
fun ProfileDetailsScreen_Preview_Username() {
    MonkeyTheme {
        Surface {
            ProfileScreen(
                component = PreviewProfileComponent(
                    displayName = "nikitakrapo",
                ),
            )
        }
    }
}

fun PreviewProfileComponent(
    displayName: String = "username",
) = object : ProfileComponent {
    override val state: StateFlow<ProfileComponent.State>
        get() = MutableStateFlow(
            ProfileComponent.State(
                displayName = displayName,
                null,
            ),
        )

    override fun onEditClicked() {}
    override fun onLogoutClicked() {}
}

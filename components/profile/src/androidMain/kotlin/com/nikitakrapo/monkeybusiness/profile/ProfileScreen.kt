package com.nikitakrapo.monkeybusiness.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.resources.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    component: ProfileComponent,
) {
    val state by component.state.collectAsState()

    Column(
        modifier = modifier,
    ) {
        ProfileHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            displayName = state.displayName,
            profileImageUrl = state.profileImageUrl,
            onEditClick = component::onEditClicked,
        )
        ListItem(
            modifier = Modifier
                .clickable(onClick = component::onSettingsClicked),
            headlineText = {
                Text(text = stringResource(R.string.settings_menu_item))
            },
            leadingContent = {
                Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
            }
        )
        ListItem(
            modifier = Modifier
                .clickable(onClick = component::onLogoutClicked),
            headlineText = {
                Text(text = stringResource(R.string.logout_menu_item))
            },
            leadingContent = {
                Icon(imageVector = Icons.Outlined.ExitToApp, contentDescription = null)
            }
        )
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
    override fun onSettingsClicked() {}
    override fun onLogoutClicked() {}
}

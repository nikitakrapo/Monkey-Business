package com.nikitakrapo.monkeybusiness.profile.profiledetails

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfileDetailsScreen(
    modifier: Modifier = Modifier,
    component: ProfileDetailsComponent,
) {
    Button(onClick = component::onLogoutClicked) {
        Text("Logout")
    }
}

fun PreviewProfileDetailsComponent() = object : ProfileDetailsComponent {
    override fun onLogoutClicked() {}
}
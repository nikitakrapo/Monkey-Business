package com.nikitakrapo.monkeybusiness.profile.auth.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.components.PasswordOutlinedTextField
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.profile.R
import com.nikitakrapo.monkeybusiness.profile.auth.register.RegistrationComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    component: RegistrationComponent,
) {
    val state by component.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                modifier = Modifier.width(TextFieldDefaults.MinWidth),
                enabled = !state.isLoading,
                value = state.username,
                onValueChange = component::onUsernameTextChanged,
                label = { Text(stringResource(R.string.username_hint)) },
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                modifier = Modifier.width(TextFieldDefaults.MinWidth),
                enabled = !state.isLoading,
                value = state.email,
                onValueChange = component::onEmailTextChanged,
                label = { Text(stringResource(R.string.email_hint)) },
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(4.dp))

            PasswordOutlinedTextField(
                modifier = Modifier.width(TextFieldDefaults.MinWidth),
                enabled = !state.isLoading,
                value = state.password,
                onValueChange = component::onPasswordTextChanged,
                label = { Text(stringResource(R.string.password_hint)) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            ElevatedButton(
                modifier = Modifier.align(Alignment.End),
                enabled = !state.isLoading,
                onClick = component::onRegisterClicked
            ) {
                Text(stringResource(R.string.register_common))
            }
        }

    }
}

@Preview
@Composable
fun RegistrationScreen_Preview() {
    MonkeyTheme {
        Surface {
            RegistrationScreen(component = PreviewRegistrationComponent())
        }
    }
}

fun PreviewRegistrationComponent() = object : RegistrationComponent {
    override val state: StateFlow<RegistrationComponent.State>
        get() = MutableStateFlow(
            RegistrationComponent.State(
                username = "username",
                email = "email@email.com",
                password = "samplepass",
                isLoading = false,
                error = null
            )
        )

    override fun onUsernameTextChanged(text: String) {}
    override fun onEmailTextChanged(text: String) {}
    override fun onPasswordTextChanged(text: String) {}
    override fun onRegisterClicked() {}
}
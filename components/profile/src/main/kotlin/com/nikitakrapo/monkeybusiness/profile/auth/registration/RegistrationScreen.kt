package com.nikitakrapo.monkeybusiness.profile.auth.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nikitakrapo.monkeybusiness.design.components.PasswordOutlinedTextField
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.profile.R
import com.nikitakrapo.monkeybusiness.profile.auth.register.RegistrationComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// TODO: unite with LoginScreen as it has too many common ui
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    component: RegistrationComponent,
) {
    val state by component.state.collectAsState()

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .zIndex(Float.MAX_VALUE)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.scrim),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    onClick = {
                        focusManager.clearFocus()
                    },
                    indication = null
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .width(TextFieldDefaults.MinWidth)
                        .focusRequester(focusRequester),
                    enabled = !state.isLoading,
                    value = state.username,
                    onValueChange = component::onUsernameTextChanged,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    label = { Text(stringResource(R.string.username_hint)) },
                    singleLine = true,
                )

                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .width(TextFieldDefaults.MinWidth)
                        .focusRequester(focusRequester),
                    enabled = !state.isLoading,
                    value = state.email,
                    onValueChange = component::onEmailTextChanged,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    label = { Text(stringResource(R.string.email_hint)) },
                    singleLine = true,
                )

                Spacer(modifier = Modifier.height(4.dp))

                PasswordOutlinedTextField(
                    modifier = Modifier
                        .width(TextFieldDefaults.MinWidth)
                        .focusRequester(focusRequester),
                    enabled = !state.isLoading,
                    value = state.password,
                    onValueChange = component::onPasswordTextChanged,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            component.onRegisterClicked()
                        }
                    ),
                    label = { Text(stringResource(R.string.password_hint)) }
                )

                state.error?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.width(TextFieldDefaults.MinWidth),
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                    )
                }

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

@Preview
@Composable
fun RegistrationScreen_Preview_Loading() {
    MonkeyTheme {
        Surface {
            RegistrationScreen(
                component = PreviewRegistrationComponent(
                    isLoading = true
                )
            )
        }
    }
}

fun PreviewRegistrationComponent(
    isLoading: Boolean = false,
) = object : RegistrationComponent {
    override val state: StateFlow<RegistrationComponent.State>
        get() = MutableStateFlow(
            RegistrationComponent.State(
                username = "username",
                email = "email@email.com",
                password = "samplepass",
                isLoading = isLoading,
                error = "General error"
            )
        )

    override fun onUsernameTextChanged(text: String) {}
    override fun onEmailTextChanged(text: String) {}
    override fun onPasswordTextChanged(text: String) {}
    override fun onRegisterClicked() {}
}

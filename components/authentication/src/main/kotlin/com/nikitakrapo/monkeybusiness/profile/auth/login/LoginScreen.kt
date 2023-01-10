package com.nikitakrapo.monkeybusiness.profile.auth.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.material3.TextButton
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
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.nikitakrapo.monkeybusiness.authentication.R
import com.nikitakrapo.monkeybusiness.design.components.PasswordOutlinedTextField
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// TODO: unite with RegistrationScreen as it has too many common ui
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    component: LoginComponent,
) {
    val state by component.state.collectAsState()

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .clickable(
                interactionSource = MutableInteractionSource(),
                onClick = {
                    focusManager.clearFocus()
                },
                indication = null
            )
    ) {
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            enter = fadeIn(),
            exit = fadeOut(),
            visible = state.isLoading
        ) {
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
                .width(TextFieldDefaults.MinWidth)
                .align(Alignment.Center)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    onClick = {
                        focusManager.clearFocus()
                    },
                    indication = null
                ),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                enabled = !state.isLoading,
                value = state.emailText,
                onValueChange = component::onEmailTextChanged,
                label = { Text(stringResource(R.string.email_hint)) },
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(4.dp))

            PasswordOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                enabled = !state.isLoading,
                value = state.passwordText,
                onValueChange = component::onPasswordTextChanged,
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        component.onLoginClicked()
                    }
                ),
                label = { Text(stringResource(R.string.password_hint)) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            AnimatedVisibility(
                visible = state.error?.isNotEmpty() == true
            ) {
                Row {
                    Text(
                        text = state.error.orEmpty(),
                        color = MaterialTheme.colorScheme.error,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    enabled = !state.isLoading,
                    onClick = {
                        focusManager.clearFocus()
                        component.onRegistrationClicked()
                    }
                ) {
                    Text(stringResource(R.string.create_account))
                }

                ElevatedButton(
                    enabled = !state.isLoading,
                    onClick = {
                        focusManager.clearFocus()
                        component.onLoginClicked()
                    }
                ) {
                    Text(stringResource(R.string.login_common))
                }
            }
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
fun LoginScreen_Preview() {
    MonkeyTheme {
        Surface {
            LoginScreen(
                component = PreviewLoginComponent()
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
fun LoginScreen_Preview_Loading() {
    MonkeyTheme {
        Surface {
            LoginScreen(
                component = PreviewLoginComponent(
                    isLoading = true,
                )
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
fun LoginScreen_Preview_Error() {
    MonkeyTheme {
        Surface {
            LoginScreen(
                component = PreviewLoginComponent(
                    error = "Your email is badly formatted"
                )
            )
        }
    }
}

fun PreviewLoginComponent(
    isLoading: Boolean = false,
    error: String? = null,
) = object : LoginComponent {
    override val state: StateFlow<LoginComponent.State>
        get() = MutableStateFlow(
            LoginComponent.State(
                emailText = "",
                passwordText = "",
                isLoading = isLoading,
                error = error
            )
        )

    override fun onEmailTextChanged(text: String) {}
    override fun onPasswordTextChanged(text: String) {}
    override fun onLoginClicked() {}
    override fun onRegistrationClicked() {}
}

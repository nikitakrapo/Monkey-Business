package com.nikitakrapo.monkeybusiness.profile.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.nikitakrapo.monkeybusiness.design.components.PasswordOutlinedTextField
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.profile.R
import com.nikitakrapo.monkeybusiness.profile.auth.login.LoginComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    component: LoginComponent,
) {
    val state by component.state.collectAsState()

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize(),
    ) {
        val (email, inputsSpacer, password, buttonsSpacer, buttonsRow, error) = createRefs()

        createVerticalChain(
            email,
            inputsSpacer,
            password,
            buttonsSpacer,
            buttonsRow,
            error,
            chainStyle = ChainStyle.Packed
        )

        OutlinedTextField(
            modifier = Modifier
                .width(TextFieldDefaults.MinWidth)
                .constrainAs(email) {
                    centerHorizontallyTo(parent)
                },
            enabled = !state.isLoading,
            value = state.emailText,
            onValueChange = component::onEmailTextChanged,
            label = { Text(stringResource(R.string.email_hint)) },
            singleLine = true,
        )

        Spacer(
            modifier = Modifier
                .constrainAs(inputsSpacer) {}
                .height(4.dp)
        )

        PasswordOutlinedTextField(
            modifier = Modifier
                .width(TextFieldDefaults.MinWidth)
                .constrainAs(password) {
                    centerHorizontallyTo(parent)
                },
            enabled = !state.isLoading,
            value = state.passwordText,
            onValueChange = component::onPasswordTextChanged,
            label = { Text(stringResource(R.string.password_hint)) }
        )

        Spacer(
            modifier = Modifier
                .constrainAs(buttonsSpacer) {}
                .height(12.dp)
        )

        Row(
            modifier = Modifier
                .constrainAs(buttonsRow) {
                    start.linkTo(password.start)
                    end.linkTo(password.end)
                    width = Dimension.fillToConstraints
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = component::onRegistrationClicked) {
                Text(stringResource(R.string.create_account))
            }

            ElevatedButton(
                enabled = !state.isLoading,
                onClick = component::onLoginClicked
            ) {
                Text(stringResource(R.string.login_common))
            }
        }

        Text(
            modifier = Modifier.constrainAs(error) {
                start.linkTo(password.start)
            },
            text = state.error.orEmpty(),
            color = MaterialTheme.colorScheme.error,
        )
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

fun PreviewLoginComponent() = object : LoginComponent {
    override val state: StateFlow<LoginComponent.State>
        get() = MutableStateFlow(LoginComponent.State("", "", false))

    override fun onEmailTextChanged(text: String) {}
    override fun onPasswordTextChanged(text: String) {}
    override fun onLoginClicked() {}
    override fun onRegistrationClicked() {}
}
package com.nikitakrapo.monkeybusiness.profile.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.nikitakrapo.monkeybusiness.design.components.PasswordOutlinedTextField
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    component: LoginComponent,
) {
    ConstraintLayout(
        modifier = modifier,
    ) {
        val (email, inputsSpacer, password, buttonsSpacer, buttonsRow) = createRefs()

        createVerticalChain(
            email,
            inputsSpacer,
            password,
            buttonsSpacer,
            buttonsRow,
            chainStyle = ChainStyle.Packed
        )

        OutlinedTextField(
            modifier = Modifier
                .constrainAs(email) {
                    centerHorizontallyTo(parent)
                },
            value = "",
            onValueChange = {},
            label = { Text("Email") }
        )

        Spacer(
            modifier = Modifier
                .constrainAs(inputsSpacer) {}
                .height(4.dp)
        )

        PasswordOutlinedTextField(
            modifier = Modifier
                .constrainAs(password) {
                    centerHorizontallyTo(parent)
                },
            value = "",
            onValueChange = {},
            label = { Text("Password") }
        )

        Spacer(
            modifier = Modifier
                .constrainAs(buttonsSpacer) {}
                .height(8.dp)
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
            TextButton(onClick = component::onRegisterClicked) {
                Text("Register")
            }

            ElevatedButton(onClick = component::onLoginClicked) {
                Text("Login")
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

fun PreviewLoginComponent() = object : LoginComponent {
    override fun onLoginClicked() {}
    override fun onRegisterClicked() {}
}
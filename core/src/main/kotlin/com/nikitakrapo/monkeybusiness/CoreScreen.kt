package com.nikitakrapo.monkeybusiness

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.monkeybusiness.design.components.bottomsheet.BottomSheet
import com.nikitakrapo.monkeybusiness.design.components.bottomsheet.BottomSheetParams
import com.nikitakrapo.monkeybusiness.design.components.bottomsheet.BottomSheetType
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finances.accounts.opening.BankAccountOpeningScreen
import com.nikitakrapo.monkeybusiness.finances.accounts.opening.PreviewBankAccountOpeningComponent
import com.nikitakrapo.monkeybusiness.finances.opening.ProductOpeningScreen
import com.nikitakrapo.monkeybusiness.finances.transactions.TransactionAddScreen
import com.nikitakrapo.monkeybusiness.home.HomeScreen
import com.nikitakrapo.monkeybusiness.home.PreviewHomeComponent
import com.nikitakrapo.monkeybusiness.profile.auth.AuthScreen
import com.nikitakrapo.monkeybusiness.profile.auth.PreviewAuthComponent
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun CoreScreen(
    modifier: Modifier = Modifier,
    component: CoreComponent,
) {
    val state by component.state.collectAsState()
    val childStack by component.childStack.collectAsState()
    val modalChildStack by component.modalChildStack.collectAsState()

    BoxWithConstraints(
        modifier = modifier,
    ) {
        Children(
            stack = childStack,
            modifier = Modifier
                .fillMaxSize(),
            animation = stackAnimation(fade()),
        ) { createdChild ->
            when (val child = createdChild.instance) {
                is CoreComponent.Child.Home ->
                    HomeScreen(component = child.component)

                is CoreComponent.Child.BankAccountOpening ->
                    BankAccountOpeningScreen(component = child.component)

                is CoreComponent.Child.Authentication ->
                    AuthScreen(component = child.component)
            }
        }

        val hasModal = remember(modalChildStack.active.instance) {
            modalChildStack.active.instance !is CoreComponent.ModalChild.None
        }
        val offsetAnchors = remember(modalChildStack.active.configuration) {
            when (modalChildStack.active.instance) {
                CoreComponent.ModalChild.None -> mapOf(0.dp to 0, Int.MAX_VALUE.dp to 1)
                is CoreComponent.ModalChild.ProfileEdit -> mapOf(0.dp to 0, 360.dp to 1)
                is CoreComponent.ModalChild.TransactionAdd -> mapOf(0.dp to 0, maxHeight - 16.dp to 1)
                is CoreComponent.ModalChild.ProductOpening -> mapOf(0.dp to 0, 180.dp to 1)
            }
        }
        BottomSheet(
            modifier = Modifier
                .fillMaxSize(),
            params = BottomSheetParams(
                type = BottomSheetType.Modal,
                offsetAnchors = offsetAnchors,
                initialState = if (hasModal) 0 else 1,
            ),
            enabled = hasModal,
            isDismissing = state.isModalDismissing,
            onDismiss = component::dismissModalInstantly,
        ) {
            when (val child = modalChildStack.active.instance) {
                is CoreComponent.ModalChild.TransactionAdd -> TransactionAddScreen(
                    modifier = Modifier.dragHandlePadding(),
                    component = child.component,
                )
                is CoreComponent.ModalChild.ProfileEdit -> ProfileEditScreen(
                    component = child.component,
                )
                is CoreComponent.ModalChild.ProductOpening -> ProductOpeningScreen(
                    component = child.component,
                )
                CoreComponent.ModalChild.None -> {}
            }
        }
    }
}

private fun Modifier.dragHandlePadding() = padding(top = 16.dp)

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
fun CoreScreen_Preview_Home() {
    MonkeyTheme {
        Surface {
            CoreScreen(
                modifier = Modifier.fillMaxSize(),
                component = PreviewCoreComponent(CoreComponentImpl.CoreScreen.Home),
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
fun CoreScreen_Preview_BankAccountOpening() {
    MonkeyTheme {
        Surface {
            CoreScreen(
                modifier = Modifier.fillMaxSize(),
                component = PreviewCoreComponent(CoreComponentImpl.CoreScreen.BankAccountOpening),
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
fun CoreScreen_Preview_Authentication() {
    MonkeyTheme {
        Surface {
            CoreScreen(
                modifier = Modifier.fillMaxSize(),
                component = PreviewCoreComponent(CoreComponentImpl.CoreScreen.Authentication),
            )
        }
    }
}

internal fun PreviewCoreComponent(
    child: CoreComponentImpl.CoreScreen,
) = object : CoreComponent {
    override val state: StateFlow<CoreComponent.State>
        get() = MutableStateFlow(CoreComponent.State(isModalDismissing = false))

    override val childStack: StateFlow<ChildStack<CoreComponentImpl.CoreScreen, CoreComponent.Child>>
        get() = MutableStateFlow(
            ChildStack(
                configuration = child,
                instance = when (child) {
                    CoreComponentImpl.CoreScreen.Home -> CoreComponent.Child.Home(
                        PreviewHomeComponent(),
                    )
                    CoreComponentImpl.CoreScreen.BankAccountOpening -> CoreComponent.Child.BankAccountOpening(
                        PreviewBankAccountOpeningComponent(),
                    )
                    CoreComponentImpl.CoreScreen.Authentication -> CoreComponent.Child.Authentication(
                        PreviewAuthComponent(),
                    )
                },
            ),
        )
    override val modalChildStack: StateFlow<ChildStack<*, CoreComponent.ModalChild>>
        get() = MutableStateFlow(
            ChildStack(
                configuration = CoreComponentImpl.CoreModalScreen.None,
                instance = CoreComponent.ModalChild.None,
            ),
        )

    override fun dismissModalInstantly() {}
}

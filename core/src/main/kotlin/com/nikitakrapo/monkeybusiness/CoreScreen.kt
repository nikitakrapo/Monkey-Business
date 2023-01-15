package com.nikitakrapo.monkeybusiness

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.home.HomeScreen
import com.nikitakrapo.monkeybusiness.home.PreviewHomeComponent
import com.nikitakrapo.monkeybusiness.modals.ModalViewsContainer
import com.nikitakrapo.monkeybusiness.modals.slideVertically
import com.nikitakrapo.monkeybusiness.profile.auth.AuthScreen
import com.nikitakrapo.monkeybusiness.profile.auth.PreviewAuthComponent
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun CoreScreen(
    modifier: Modifier = Modifier,
    component: CoreComponent
) {
    val childStack by component.childStack.collectAsState()
    val modalChildStack by component.modalChildStack.collectAsState()

    Box(
        modifier = modifier,
    ) {
        Children(
            stack = childStack,
            modifier = Modifier
                .fillMaxSize(),
            animation = stackAnimation(fade())
        ) { createdChild ->
            when (val child = createdChild.instance) {
                is CoreComponent.Child.Home -> HomeScreen(component = child.component)
                is CoreComponent.Child.Authentication -> AuthScreen(component = child.component)
            }
        }

        //TODO: Add scrim to modal bg.
        Children(
            stack = modalChildStack,
            modifier = Modifier,
            animation = stackAnimation(slideVertically())
        ) { createdModalChild ->
            ModalViewsContainer(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                when (val child = createdModalChild.instance) {
                    is CoreComponent.ModalChild.ProfileEdit -> ProfileEditScreen(
                        component = child.component,
                    )
                    CoreComponent.ModalChild.None -> {}
                }
            }
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720
)
@Composable
fun CoreScreen_Preview_Home() {
    MonkeyTheme {
        Surface {
            CoreScreen(
                modifier = Modifier.fillMaxSize(),
                component = PreviewCoreComponent(CoreComponentImpl.CoreScreen.Home)
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720
)
@Composable
fun CoreScreen_Preview_Authentication() {
    MonkeyTheme {
        Surface {
            CoreScreen(
                modifier = Modifier.fillMaxSize(),
                component = PreviewCoreComponent(CoreComponentImpl.CoreScreen.Authentication)
            )
        }
    }
}

internal fun PreviewCoreComponent(
    child: CoreComponentImpl.CoreScreen
) = object : CoreComponent {
    override val childStack: StateFlow<ChildStack<CoreComponentImpl.CoreScreen, CoreComponent.Child>>
        get() = MutableStateFlow(
            ChildStack(
                configuration = child,
                instance = when (child) {
                    CoreComponentImpl.CoreScreen.Home -> CoreComponent.Child.Home(
                        PreviewHomeComponent()
                    )
                    CoreComponentImpl.CoreScreen.Authentication -> CoreComponent.Child.Authentication(
                        PreviewAuthComponent()
                    )
                }
            )
        )
    override val modalChildStack: StateFlow<ChildStack<*, CoreComponent.ModalChild>>
        get() = MutableStateFlow(
            ChildStack(
                configuration = CoreComponentImpl.CoreModalScreen.None,
                instance = CoreComponent.ModalChild.None
            )
        )
}

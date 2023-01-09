package com.nikitakrapo.monkeybusiness.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.account.Account
import com.nikitakrapo.monkeybusiness.design.components.BottomNavigationBar
import com.nikitakrapo.monkeybusiness.design.components.NavigationBarItemModel
import com.nikitakrapo.monkeybusiness.design.icons.Wallet
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finances.FinancesScreen
import com.nikitakrapo.monkeybusiness.finances.PreviewFinancesComponent
import com.nikitakrapo.monkeybusiness.profile.PreviewProfileComponent
import com.nikitakrapo.monkeybusiness.profile.ProfileScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    component: HomeComponent
) {
    val childStack by component.childStack.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Children(
            stack = childStack,
            modifier = Modifier.fillMaxWidth(),
            animation = homeTabAnimation()
        ) { createdChild ->
            when (val child = createdChild.instance) {
                is HomeComponent.Child.Finances -> FinancesScreen(
                    modifier = Modifier.fillMaxWidth(),
                    component = child.component
                )
                is HomeComponent.Child.Profile -> ProfileScreen(
                    modifier = Modifier.fillMaxWidth(),
                    component = child.component
                )
            }
        }
        BottomNavigationBar(
            items = listOf(
                NavigationBarItemModel(
                    selected = childStack.active.instance is HomeComponent.Child.Finances,
                    onClick = component::onFinancesClicked,
                    icon = Icons.Default.Wallet,
                    iconContentDescription = stringResource(R.string.cd_finances)
                ),
                NavigationBarItemModel(
                    selected = childStack.active.instance is HomeComponent.Child.Profile,
                    onClick = component::onProfileClicked,
                    icon = Icons.Default.Person,
                    iconContentDescription = stringResource(R.string.cd_profile)
                )
            ),
            windowInsets = WindowInsets.navigationBars
        )
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
fun HomeScreen_Preview_Finances() {
    MonkeyTheme {
        Surface {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                component = PreviewHomeComponent(HomeComponentImpl.HomeScreen.Finances)
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
fun HomeScreen_Preview_Profile() {
    MonkeyTheme {
        Surface {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                component = PreviewHomeComponent(
                    HomeComponentImpl.HomeScreen.Profile(
                        Account.Emailish(
                            uid = "",
                            email = "sample@email.com"
                        )
                    )
                )
            )
        }
    }
}

// TODO: figure out what to do with preview components
fun PreviewHomeComponent(
    screen: HomeComponentImpl.HomeScreen = HomeComponentImpl.HomeScreen.Finances,
) = object : HomeComponent {
    override val childStack: StateFlow<ChildStack<HomeComponentImpl.HomeScreen, HomeComponent.Child>>
        get() = MutableStateFlow(
            ChildStack(
                configuration = screen,
                instance = when (screen) {
                    HomeComponentImpl.HomeScreen.Finances -> HomeComponent.Child.Finances(
                        PreviewFinancesComponent()
                    )
                    is HomeComponentImpl.HomeScreen.Profile -> HomeComponent.Child.Profile(
                        PreviewProfileComponent()
                    )
                }
            )
        )

    override fun onFinancesClicked() {}
    override fun onProfileClicked() {}
}

package com.nikitakrapo.monkeybusiness.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.nikitakrapo.monkeybusiness.analytics.PreviewAnalyticsComponent
import com.nikitakrapo.monkeybusiness.design.components.BottomNavigationBar
import com.nikitakrapo.monkeybusiness.design.components.NavigationBarItemModel
import com.nikitakrapo.monkeybusiness.design.icons.filled.Analytics
import com.nikitakrapo.monkeybusiness.design.icons.filled.Wallet
import com.nikitakrapo.monkeybusiness.design.icons.outlined.Analytics
import com.nikitakrapo.monkeybusiness.design.icons.outlined.Wallet
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
    component: HomeComponent,
) {
    val childStack by component.childStack.collectAsState()

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars),
    ) {
        val (child, bottomNav) = createRefs()

        Children(
            modifier = Modifier
                .constrainAs(child) {
                    top.linkTo(parent.top)
                    bottom.linkTo(bottomNav.top)
                    centerHorizontallyTo(parent)
                    height = Dimension.fillToConstraints
                },
            stack = childStack,
            animation = homeTabAnimation(),
        ) { createdChild ->
            when (val child = createdChild.instance) {
                is HomeComponent.Child.Finances -> FinancesScreen(
                    modifier = Modifier.fillMaxWidth(),
                    component = child.component,
                )
                is HomeComponent.Child.Analytics -> {
                    /* TODO */
                }
                is HomeComponent.Child.Profile -> ProfileScreen(
                    modifier = Modifier.fillMaxWidth(),
                    component = child.component,
                )
            }
        }
        BottomNavigationBar(
            modifier = Modifier
                .constrainAs(bottomNav) {
                    centerHorizontallyTo(parent)
                    bottom.linkTo(parent.bottom)
                },
            items = listOf(
                run {
                    val isSelected = childStack.active.instance is HomeComponent.Child.Finances
                    NavigationBarItemModel(
                        selected = isSelected,
                        onClick = component::onFinancesClicked,
                        icon = if (isSelected) Icons.Filled.Wallet else Icons.Outlined.Wallet,
                        iconContentDescription = stringResource(R.string.cd_finances),
                    )
                },
                run {
                    val isSelected = childStack.active.instance is HomeComponent.Child.Analytics
                    NavigationBarItemModel(
                        selected = isSelected,
                        onClick = component::onAnalyticsClicked,
                        icon = if (isSelected) Icons.Filled.Analytics else Icons.Outlined.Analytics,
                        iconContentDescription = stringResource(R.string.cd_analytics),
                    )
                },
                run {
                    val isSelected = childStack.active.instance is HomeComponent.Child.Profile
                    NavigationBarItemModel(
                        selected = isSelected,
                        onClick = component::onProfileClicked,
                        icon = if (isSelected) Icons.Filled.Person else Icons.Outlined.Person,
                        iconContentDescription = stringResource(R.string.cd_profile),
                    )
                },
            ),
            windowInsets = WindowInsets.navigationBars,
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
                component = PreviewHomeComponent(HomeComponentImpl.HomeScreen.Finances),
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
                    HomeComponentImpl.HomeScreen.Profile,
                ),
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
                        PreviewFinancesComponent(),
                    )
                    HomeComponentImpl.HomeScreen.Analytics -> HomeComponent.Child.Analytics(
                        PreviewAnalyticsComponent(),
                    )
                    HomeComponentImpl.HomeScreen.Profile -> HomeComponent.Child.Profile(
                        PreviewProfileComponent(),
                    )
                },
            ),
        )

    override fun onFinancesClicked() {}
    override fun onAnalyticsClicked() {}
    override fun onProfileClicked() {}
}

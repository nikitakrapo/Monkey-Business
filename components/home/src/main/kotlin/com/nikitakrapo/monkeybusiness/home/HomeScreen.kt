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
import com.nikitakrapo.monkeybusiness.design.components.BottomNavigationBar
import com.nikitakrapo.monkeybusiness.design.components.NavigationBarItemModel
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finances.FinancesScreen
import com.nikitakrapo.monkeybusiness.finances.PreviewFinancesComponent
import com.nikitakrapo.monkeybusiness.profile.PreviewProfileComponent
import com.nikitakrapo.monkeybusiness.profile.ProfileScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    component: HomeComponent
) {
    val currentChild by component.child.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        when (val child = currentChild) {
            is HomeComponent.Child.Finances -> FinancesScreen(
                modifier = Modifier.fillMaxWidth(),
                component = child.component
            )
            is HomeComponent.Child.Profile -> ProfileScreen(
                modifier = Modifier.fillMaxWidth(),
                component = child.component
            )
        }
        BottomNavigationBar(
            items = listOf(
                NavigationBarItemModel(
                    selected = currentChild is HomeComponent.Child.Finances,
                    onClick = component::onFinancesClicked,
                    icon = Icons.Default.Home,
                    iconContentDescription = stringResource(R.string.cd_finances)
                ),
                NavigationBarItemModel(
                    selected = currentChild is HomeComponent.Child.Profile,
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
                component = PreviewHomeComponent(HomeComponentImpl.HomeScreen.Profile)
            )
        }
    }
}

// TODO: figure out what to do with preview components
fun PreviewHomeComponent(
    screen: HomeComponentImpl.HomeScreen = HomeComponentImpl.HomeScreen.Finances,
) = object : HomeComponent {
    override val child: StateFlow<HomeComponent.Child>
        get() = MutableStateFlow(
            when (screen) {
                HomeComponentImpl.HomeScreen.Finances -> HomeComponent.Child.Finances(
                    PreviewFinancesComponent()
                )
                HomeComponentImpl.HomeScreen.Profile -> HomeComponent.Child.Profile(
                    PreviewProfileComponent()
                )
            }
        )

    override fun onFinancesClicked() {}
    override fun onProfileClicked() {}
}

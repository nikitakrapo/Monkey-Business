package com.nikitakrapo.monkeybusiness

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.monkeybusiness.core.R
import com.nikitakrapo.monkeybusiness.design.components.BottomNavigationBar
import com.nikitakrapo.monkeybusiness.design.components.NavigationBarItemModel
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.home.HomeScreen
import com.nikitakrapo.monkeybusiness.home.PreviewHomeComponent
import com.nikitakrapo.monkeybusiness.profile.PreviewProfileComponent
import com.nikitakrapo.monkeybusiness.profile.ProfileScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CoreScreen(
    modifier: Modifier = Modifier,
    component: CoreComponent
) {
    val childState by component.child.collectAsState()

    Column(
        modifier = modifier
            .imePadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        when (val child = childState) {
            is CoreComponent.Child.Home -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                HomeScreen(
                    modifier = Modifier.fillMaxWidth(),
                    component = child.component
                )
                BottomNavigationBar(
                    items = listOf(
                        NavigationBarItemModel(
                            selected = true,
                            onClick = component::onHomeClicked,
                            icon = Icons.Default.Home,
                            iconContentDescription = stringResource(R.string.cd_home_screen)
                        ),
                        NavigationBarItemModel(
                            selected = false,
                            onClick = component::onMoreClicked,
                            icon = Icons.Default.MoreVert,
                            iconContentDescription = stringResource(R.string.cd_more)
                        )
                    ),
                    windowInsets = WindowInsets.navigationBars
                )
            }
            is CoreComponent.Child.More -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text("More")
                BottomNavigationBar(
                    items = listOf(
                        NavigationBarItemModel(
                            selected = false,
                            onClick = component::onHomeClicked,
                            icon = Icons.Default.Home,
                            iconContentDescription = stringResource(R.string.cd_home_screen)
                        ),
                        NavigationBarItemModel(
                            selected = true,
                            onClick = component::onMoreClicked,
                            icon = Icons.Default.MoreVert,
                            iconContentDescription = stringResource(R.string.cd_more)
                        )
                    ),
                    windowInsets = WindowInsets.navigationBars
                )
            }
            is CoreComponent.Child.Profile -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ProfileScreen(
                    modifier = Modifier.fillMaxWidth(),
                    component = child.component,
                    onBackPressed = component::onHomeClicked //TODO: make proper navigation
                )
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
                component = PreviewCoreComponent(CoreComponent.Child.Home(PreviewHomeComponent()))
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720
)
@Composable
fun CoreScreen_Preview_Profile() {
    MonkeyTheme {
        Surface {
            CoreScreen(
                modifier = Modifier.fillMaxSize(),
                component = PreviewCoreComponent(CoreComponent.Child.Profile(PreviewProfileComponent()))
            )
        }
    }
}

internal fun PreviewCoreComponent(
    child: CoreComponent.Child
) = object : CoreComponent {
    override val child: StateFlow<CoreComponent.Child>
        get() = MutableStateFlow(child)

    override fun onHomeClicked() {}
    override fun onMoreClicked() {}
}

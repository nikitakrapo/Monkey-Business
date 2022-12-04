package com.nikitakrapo.monkeybusiness

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.monkeybusiness.core.R
import com.nikitakrapo.monkeybusiness.design.BottomNavigationBar
import com.nikitakrapo.monkeybusiness.design.NavigationBarItemModel
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.home.HomeScreen
import com.nikitakrapo.monkeybusiness.home.PreviewHomeComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CoreScreen(
    modifier: Modifier = Modifier,
    component: CoreComponent
) {
    val childState = component.child.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        when (val child = childState.value) {
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
                            iconContentDescription = stringResource(R.string.cd_home)
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
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text("More")
                BottomNavigationBar(
                    items = listOf(
                        NavigationBarItemModel(
                            selected = false,
                            onClick = component::onHomeClicked,
                            icon = Icons.Default.Home,
                            iconContentDescription = stringResource(R.string.cd_home)
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
                Text("Profile")
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
                component = PreviewCoreComponent(CoreComponent.Child.Profile(Unit))
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

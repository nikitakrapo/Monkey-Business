package com.nikitakrapo.monkeybusiness

import androidx.compose.foundation.layout.*
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
import com.nikitakrapo.monkeybusiness.design.BottomNavigationBar
import com.nikitakrapo.monkeybusiness.design.NavigationBarItemModel
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.home.HomeScreen

@Composable
fun CoreScreen(
    modifier: Modifier = Modifier,
    component: CoreComponent,
) {
    val childState = component.child.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        when (val child = childState.value) {
            is CoreComponent.Child.Home -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                HomeScreen(
                    modifier = Modifier.fillMaxWidth(),
                    component = child.component,
                )
                BottomNavigationBar(
                    items = listOf(
                        NavigationBarItemModel(
                            selected = true,
                            onClick = component::onHomeClicked,
                            icon = Icons.Default.Home,
                            iconContentDescription = stringResource(R.string.cd_home),
                        ),
                        NavigationBarItemModel(
                            selected = false,
                            onClick = component::onMoreClicked,
                            icon = Icons.Default.MoreVert,
                            iconContentDescription = stringResource(R.string.cd_more),
                        ),
                    ),
                    windowInsets = WindowInsets.navigationBars
                )
            }
            is CoreComponent.Child.More -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("More")
                BottomNavigationBar(
                    items = listOf(
                        NavigationBarItemModel(
                            selected = false,
                            onClick = component::onHomeClicked,
                            icon = Icons.Default.Home,
                            iconContentDescription = stringResource(R.string.cd_home),
                        ),
                        NavigationBarItemModel(
                            selected = true,
                            onClick = component::onMoreClicked,
                            icon = Icons.Default.MoreVert,
                            iconContentDescription = stringResource(R.string.cd_more),
                        ),
                    ),
                    windowInsets = WindowInsets.navigationBars
                )
            }
            is CoreComponent.Child.Profile -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("Profile")
            }
        }
    }
}

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
                component = CoreComponentImpl(initialScreen = CoreComponentImpl.CoreScreen.HOME),
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
fun CoreScreen_Preview_Profile() {
    MonkeyTheme {
        Surface {
            CoreScreen(
                modifier = Modifier.fillMaxSize(),
                component = CoreComponentImpl(initialScreen = CoreComponentImpl.CoreScreen.MORE),
            )
        }
    }
}

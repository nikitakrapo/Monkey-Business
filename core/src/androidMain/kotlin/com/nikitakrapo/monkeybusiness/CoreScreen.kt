package com.nikitakrapo.monkeybusiness

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nikitakrapo.monkeybusiness.design.BottomNavigationBar
import com.nikitakrapo.monkeybusiness.design.NavigationBarItemModel

@Composable
fun CoreScreen(
    modifier: Modifier = Modifier,
    component: Core,
) {
    val state = component.state.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        when (state.value.child) {
            Core.Child.Home -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("Home")
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
                            onClick = component::onProfileClicked,
                            icon = Icons.Default.Person,
                            iconContentDescription = stringResource(R.string.cd_profile),
                        ),
                    ),
                    windowInsets = WindowInsets.navigationBars
                )
            }
            Core.Child.Profile -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("Profile")
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
                            onClick = component::onProfileClicked,
                            icon = Icons.Default.Person,
                            iconContentDescription = stringResource(R.string.cd_profile),
                        ),
                    ),
                    windowInsets = WindowInsets.navigationBars
                )
            }
        }
    }
}
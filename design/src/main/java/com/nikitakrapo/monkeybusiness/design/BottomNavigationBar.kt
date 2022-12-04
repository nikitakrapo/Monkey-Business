package com.nikitakrapo.monkeybusiness.design

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    items: List<NavigationBarItemModel>,
    windowInsets: WindowInsets
) {
    BottomAppBar(
        modifier = modifier,
        actions = {
            items.forEach { item ->
                NavigationBarItem(
                    selected = item.selected,
                    onClick = item.onClick,
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.iconContentDescription
                        )
                    }
                )
            }
        },
        windowInsets = windowInsets
    )
}

class NavigationBarItemModel(
    val selected: Boolean,
    val onClick: () -> Unit,
    val icon: ImageVector,
    val iconContentDescription: String
)

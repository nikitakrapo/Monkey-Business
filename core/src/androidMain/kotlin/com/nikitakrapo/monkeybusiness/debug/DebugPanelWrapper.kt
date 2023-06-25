package com.nikitakrapo.monkeybusiness.debug

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DebugPanelWrapper(
    modifier: Modifier = Modifier,
    component: DebugPanelComponent,
    content: @Composable () -> Unit,
) {
    val state by component.state.collectAsState()
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed,
        confirmStateChange = {
            when (it) {
                DrawerValue.Closed -> component.close()
                DrawerValue.Open -> component.open()
            }
            true
        }
    )
    LaunchedEffect(state.panelOpened) {
        if (state.panelOpened) {
            drawerState.open()
        } else {
            drawerState.close()
        }
    }
    Box {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet(
                    modifier = modifier
                        .width(260.dp),
                    content = {
                        ListItem(
                            modifier = Modifier
                                .clickable { component.copyUid() },
                            headlineText = { Text("Copy UID") }
                        )
                        ListItem(
                            modifier = Modifier
                                .clickable { component.copyAuthToken() },
                            headlineText = { Text("Copy Authorization token") }
                        )
                        ListItem(
                            modifier = Modifier
                                .clickable { error("Testing crash analytics") },
                            headlineText = { Text("Crash app") },
                            supportingText = { Text("Is used to test Crashlytics") },
                        )
                    },
                )
            },
            content = content,
            drawerState = drawerState,
            gesturesEnabled = state.panelOpened,
        )
        val buttonOffsetX by animateDpAsState(targetValue = if (state.panelOpened) 260.dp else 0.dp)
        Box(
            modifier = Modifier
                .offset(x = buttonOffsetX)
                .align(Alignment.CenterStart)
                .size(32.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                .clickable {
                    if (state.panelOpened) component.close() else component.open()
                },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
            )
        }
    }
}

fun PreviewDebugPanelComponent(
    isOpened: Boolean = false,
) = object : DebugPanelComponent {
    override val state: StateFlow<DebugPanelComponent.State>
        get() = MutableStateFlow(DebugPanelComponent.State(isOpened))

    override fun open() {}
    override fun close() {}

    override fun copyUid() {}
    override fun copyAuthToken() {}
}
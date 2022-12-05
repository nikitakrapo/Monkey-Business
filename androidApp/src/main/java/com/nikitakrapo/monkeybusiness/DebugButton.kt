package com.nikitakrapo.monkeybusiness

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun DebugButton(
    modifier: Modifier = Modifier
) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    var debugViewExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
            .clip(RoundedCornerShape(20))
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            .animateContentSize()
    ) {
        Row(
            modifier = Modifier
                .defaultMinSize(minWidth = if (debugViewExpanded) 200.dp else 0.dp)
        ) {
            IconButton(
                modifier = Modifier.clip(CircleShape),
                onClick = { debugViewExpanded = !debugViewExpanded }
            ) {
                Crossfade(targetState = debugViewExpanded) { isExpanded ->
                    if (isExpanded) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = null
                        )
                    }
                }
            }

            if (debugViewExpanded) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "Debug panel",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        if (debugViewExpanded) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                DebugItem(
                    titleText = "Crash app",
                    subtitleText = "Check crashlytics",
                    onClick = { throw Exception("Crash from debug panel") }
                )
            }
        }
    }
}

@Composable
fun DebugItem(
    modifier: Modifier = Modifier,
    titleText: String,
    subtitleText: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(15))
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))
            .padding(6.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = titleText,
            style = MaterialTheme.typography.labelLarge
        )
        subtitleText?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

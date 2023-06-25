package com.nikitakrapo.monkeybusiness.design.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme

private val minItemHeight = 40.dp

@Composable
fun SegmentedSwitch(
    modifier: Modifier = Modifier,
    items: List<SegmentedSwitchItem>,
    enabled: Boolean = true,
) {
    require(items.size >= 2) { "There should be >= 2 items in switch" }

    val itemWeight = remember(items) { 1f / items.size }
    val itemsPadding = remember { 2.dp }

    val containerColor = if (enabled) {
        MaterialTheme.colorScheme.secondaryContainer
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
    }
    val outlineColor = if (enabled) {
        MaterialTheme.colorScheme.outline
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
    }

    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(outlineColor)
            .padding(itemsPadding),
        horizontalArrangement = Arrangement.Center
    ) {
        items.forEachIndexed { index, segmentedSwitchItem ->
            if (index != 0) {
                Spacer(modifier = Modifier.width(itemsPadding))
            }
            val alpha by animateFloatAsState(
                targetValue = if (segmentedSwitchItem.isSelected) 0.4f else 0f
            )
            Row(
                modifier = Modifier
                    .weight(itemWeight)
                    .heightIn(min = minItemHeight)
                    .segmentItem(
                        itemIndex = index,
                        lastIndex = items.lastIndex
                    )
                    .background(
                        color = if (segmentedSwitchItem.isSelected) {
                            containerColor
                                .copy(alpha = alpha)
                                .compositeOver(MaterialTheme.colorScheme.surface)
                        } else {
                            MaterialTheme.colorScheme.surface
                        }
                    )
                    .clickable(enabled = enabled) { segmentedSwitchItem.onSelect() },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val contentColor = if (enabled) {
                    MaterialTheme.colorScheme.onSecondaryContainer
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                }
                CompositionLocalProvider(LocalContentColor provides contentColor) {
                    segmentedSwitchItem.label()
                }
            }
        }
    }
}

@Composable
internal fun Modifier.segmentItem(
    itemIndex: Int,
    lastIndex: Int
): Modifier {
    return when (itemIndex) {
        0 -> {
            val shape = CircleShape.copy(
                topEnd = CornerSize(0),
                bottomEnd = CornerSize(0)
            )
            clip(shape)
        }
        lastIndex -> {
            val shape = CircleShape.copy(
                topStart = CornerSize(0),
                bottomStart = CornerSize(0)
            )
            clip(shape)
        }
        else -> this
    }
}

data class SegmentedSwitchItem(
    val label: @Composable () -> Unit,
    val isSelected: Boolean,
    val onSelect: () -> Unit,
)

@Preview(name = "Big items")
@Composable
fun SegmentedSwitch_Preview_BigItems() {
    var selectedItem by remember { mutableStateOf(0) }
    MonkeyTheme {
        Surface {
            SegmentedSwitch(
                items = listOf(
                    SegmentedSwitchItem(
                        label = {
                            Text(
                                modifier = Modifier
                                    .size(64.dp)
                                    .background(Color.Cyan),
                                text = "item1"
                            )
                        },
                        isSelected = selectedItem == 0,
                    ) { selectedItem = 0 },
                    SegmentedSwitchItem(
                        label = {
                            Text(
                                modifier = Modifier
                                    .size(64.dp)
                                    .background(Color.Cyan),
                                text = "item2"
                            )
                        },
                        isSelected = selectedItem == 1,
                    ) { selectedItem = 1 }
                )
            )
        }
    }
}

@Preview(name = "2 Items")
@Composable
fun SegmentedSwitch_Preview2() {
    var selectedItem by remember { mutableStateOf(0) }
    MonkeyTheme {
        Surface {
            SegmentedSwitch(
                items = listOf(
                    SegmentedSwitchItem(
                        label = { Text("item1") },
                        isSelected = selectedItem == 0,
                    ) { selectedItem = 0 },
                    SegmentedSwitchItem(
                        label = { Text("item2") },
                        isSelected = selectedItem == 1,
                    ) { selectedItem = 1 }
                )
            )
        }
    }
}

@Preview(name = "3 Items")
@Composable
fun SegmentedSwitch_Preview3() {
    var selectedItem by remember { mutableStateOf(0) }
    MonkeyTheme {
        Surface {
            SegmentedSwitch(
                items = listOf(
                    SegmentedSwitchItem(
                        label = { Text("item1") },
                        isSelected = selectedItem == 0,
                    ) { selectedItem = 0 },
                    SegmentedSwitchItem(
                        label = { Text("item2") },
                        isSelected = selectedItem == 1,
                    ) { selectedItem = 1 },
                    SegmentedSwitchItem(
                        label = { Text("item3") },
                        isSelected = selectedItem == 2,
                    ) { selectedItem = 2 },
                )
            )
        }
    }
}


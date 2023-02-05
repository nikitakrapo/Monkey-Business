package com.nikitakrapo.monkeybusiness.design.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.BorderSegment
import com.nikitakrapo.monkeybusiness.design.segmentedBorder
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme

private val defaultItemSize = DpSize(
    width = 92.dp,
    height = 46.dp
)

@Composable
fun SegmentedSwitch(
    modifier: Modifier = Modifier,
    items: List<SegmentedSwitchItem>,
    itemSize: DpSize = defaultItemSize,
) {
    require(items.size >= 2) { "There should be >= 2 items in switch" }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        items.forEachIndexed { index, segmentedSwitchItem ->
            val alpha by animateFloatAsState(
                targetValue = if (segmentedSwitchItem.isSelected) 0.4f else 0f
            )
            Row(
                modifier = Modifier
                    .size(itemSize)
                    .segmentItem(
                        itemIndex = index,
                        lastIndex = items.lastIndex
                    )
                    .background(
                        color = if (segmentedSwitchItem.isSelected) {
                            MaterialTheme.colorScheme.secondaryContainer.copy(alpha = alpha)
                        } else {
                            Color.Transparent
                        }
                    )
                    .clickable { segmentedSwitchItem.onSelect() },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                segmentedSwitchItem.label()
            }
        }
    }
}

@Composable
internal fun Modifier.segmentItem(
    strokeWidth: Dp = 2.dp,
    strokeColor: Color = MaterialTheme.colorScheme.outline,
    itemIndex: Int,
    lastIndex: Int
): Modifier {
    return when (itemIndex) {
        0 -> {
            val shape = CircleShape.copy(
                topEnd = CornerSize(0),
                bottomEnd = CornerSize(0)
            )
            clip(shape).border(width = strokeWidth, color = strokeColor, shape = shape)
        }
        lastIndex -> {
            val shape = CircleShape.copy(
                topStart = CornerSize(0),
                bottomStart = CornerSize(0)
            )
            clip(shape).border(width = strokeWidth, color = strokeColor, shape = shape)
        }
        else -> segmentedBorder(
            strokeWidth = strokeWidth,
            color = strokeColor,
            segments = setOf(BorderSegment.Top, BorderSegment.Bottom),
        )
    }
}

data class SegmentedSwitchItem(
    val label: @Composable () -> Unit,
    val isSelected: Boolean,
    val onSelect: () -> Unit,
)

@Preview
@Composable
fun SegmentedSwitch_Preview() {
    var selectedItem by remember { mutableStateOf(0) }
    MonkeyTheme {
        Surface {
            SegmentedSwitch(
                items = listOf(
                    SegmentedSwitchItem(
                        label = { Text("this1") },
                        isSelected = selectedItem == 0,
                    ) { selectedItem = 0 },
                    SegmentedSwitchItem(
                        label = { Text("this2") },
                        isSelected = selectedItem == 1,
                    ) { selectedItem = 1 },
                    SegmentedSwitchItem(
                        label = { Text("this3") },
                        isSelected = selectedItem == 2,
                    ) { selectedItem = 2 },
                )
            )
        }
    }
}
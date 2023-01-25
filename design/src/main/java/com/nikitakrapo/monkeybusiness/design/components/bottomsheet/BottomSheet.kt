package com.nikitakrapo.monkeybusiness.design.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

private const val DEFAULT_ANCHOR_THRESHOLD = 0.3f

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    params: BottomSheetParams,
    onDismiss: () -> Unit,
    enabled: Boolean,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()

    val anchors = remember(params) { params.offsetAnchors }
    val anchorsPx = remember(anchors) { anchors.mapKeys { with(density) { it.key.toPx() } } }
    val anchorModalClosedValue = remember(anchors) { anchors.values.max() }
    val maxHeight = remember(anchors) { anchors.maxBy { it.key }.key }
    val maxHeightPx = remember(anchors) { with(density) { maxHeight.toPx() } }

    val swipeableState = rememberSwipeableState(params.initialState)
    LaunchedEffect(params.initialState) {
        swipeableState.animateTo(params.initialState)
    }

    val isClosed = remember(swipeableState.currentValue) {
        anchorModalClosedValue == swipeableState.currentValue
    }
    LaunchedEffect(isClosed) {
        if (isClosed) onDismiss()
    }

    if (isClosed && !enabled) return
    BoxWithConstraints(
        modifier = modifier
            .swipeable(
                state = swipeableState,
                anchors = anchorsPx,
                thresholds = { _, _ -> FractionalThreshold(DEFAULT_ANCHOR_THRESHOLD) },
                orientation = Orientation.Vertical
            ),
    ) {
        if (params.type == BottomSheetType.Modal) {
            val scrimAnchorOffsetPx = remember(params.offsetAnchors) {
                with(density) { params.getFirstAnchorHeight().toPx() }
            }
            val bgAlpha = remember(swipeableState.offset.value) {
                val currentHeight = maxHeightPx - swipeableState.offset.value
                val scrimHeight = maxHeightPx - scrimAnchorOffsetPx
                ((currentHeight / scrimHeight).coerceIn(0f..1f))
            }
            BottomSheetBackground(
                modifier = Modifier.fillMaxSize(),
                alpha = bgAlpha,
            ) {
                scope.launch { swipeableState.animateTo(anchorModalClosedValue) }
            }
        }

        val yOffset = with(density) { swipeableState.offset.value.toDp().coerceAtLeast(0.dp) }
        BottomSheetContainer(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            yOffset = yOffset,
            height = maxHeight,
            content = content,
        )
    }
}

@Composable
fun BottomSheetContainer(
    modifier: Modifier = Modifier,
    yOffset: Dp,
    height: Dp,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .offset(y = yOffset)
            .clip(
                MaterialTheme.shapes.extraLarge.copy(
                    bottomEnd = ZeroCornerSize,
                    bottomStart = ZeroCornerSize
                )
            )
            .fillMaxWidth()
            .height(height)
            .background(MaterialTheme.colorScheme.surface)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {},
    ) {
        content()
        DragHandle(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 12.dp)
        )
    }
}

@Composable
fun DragHandle(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .size(
                    width = 32.dp,
                    height = 4.dp,
                )
                .clip(CircleShape)
                .background(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                        .copy(alpha = 0.4f)
                ),
        )
    }
}
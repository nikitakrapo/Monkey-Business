package com.nikitakrapo.monkeybusiness.design

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

fun Modifier.segmentedBorder(
    strokeWidth: Dp,
    color: Color,
    segments: Set<BorderSegment>,
) = composed(
    factory = {
        val density = LocalDensity.current

        val strokeWidthPx = density.run { strokeWidth.toPx() }

        //FIXME: bug
        Modifier.drawWithCache {
            onDrawWithContent {
                val width = size.width
                val height = size.height

                val strokeOffset = strokeWidthPx / 2

                drawContent()
                segments.forEach { segment ->
                    val startOffset = when (segment) {
                        BorderSegment.Top -> Offset(x = 0f, y = strokeOffset)
                        BorderSegment.Bottom -> Offset(x = 0f, y = height - strokeOffset)
                        BorderSegment.Start -> Offset(x = strokeOffset, y = 0f)
                        BorderSegment.End -> Offset(x = width - strokeOffset, y = 0f)
                    }
                    val endOffset = when (segment) {
                        BorderSegment.Top -> Offset(x = width, y = strokeOffset)
                        BorderSegment.Bottom -> Offset(x = width, y = height - strokeOffset)
                        BorderSegment.Start -> Offset(x = strokeOffset, y = height)
                        BorderSegment.End -> Offset(x = width - strokeOffset, y = height)
                    }
                    drawLine(
                        color = color,
                        start = startOffset,
                        end = endOffset,
                        strokeWidth = strokeWidthPx
                    )
                }
            }
        }
    }
)

enum class BorderSegment {
    Top, Bottom, Start, End;

    companion object {
        val vertical = setOf(Top, Bottom)
        val horizontal = setOf(Start, End)
    }
}

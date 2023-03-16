package com.nikitakrapo.monkeybusiness.analytics.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme

@Composable
fun SpendingChip(
    modifier: Modifier = Modifier,
    part: SpendingsChartPart,
    color: Color,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(color)
            .padding(
                vertical = 6.dp,
                horizontal = 12.dp
            ),
    ) {
        Text(
            text = part.name,
            color = if (color.luminance() > 0.45f) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.surface
            }
        )
    }
}

@Preview
@Composable
fun SpendingChip_Preview() {
    MonkeyTheme {
        Surface {
            SpendingChip(
                part = SpendingsChartPart(name = "Fastfood", value = 100),
                color = Color.Red
            )
        }
    }
}
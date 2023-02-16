package com.nikitakrapo.monkeybusiness.analytics.chart

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import kotlinx.coroutines.delay

@Composable
fun CircularSpendingsChart(
    modifier: Modifier = Modifier,
    parts: List<SpendingsChartPart>,
) {
    val valueSum = remember(parts) {
        parts.sumOf(SpendingsChartPart::value)
    }
    var part by remember { mutableStateOf(0f) }
    val partVisible by animateFloatAsState(
        targetValue = part,
        animationSpec = tween(
            durationMillis = 1000,
            easing = { OvershootInterpolator(0f).getInterpolation(it) }
        )
    )
    LaunchedEffect(Unit) {
        delay(100)
        part = 1f
    }
    Canvas(
        modifier = modifier
            .padding(12.dp)
    ) {
        var currentAngle = 0f
        parts.forEachIndexed { index, spendingsChartPart ->
            val angle = spendingsChartPart.value * (360f * partVisible) / valueSum
            drawArc(
                color = ChartColor.forIndex(index).color,
                startAngle = currentAngle,
                sweepAngle = angle,
                useCenter = false,
                style = Stroke(22.dp.toPx())
            )
            currentAngle += angle
        }
    }
}

@Preview
@Composable
fun CircularSpendingsChart_Preview() {
    MonkeyTheme {
        Surface {
            CircularSpendingsChart(
                modifier = Modifier.size(128.dp),
                parts = previewParts
            )
        }
    }
}

private val previewParts = listOf(
    SpendingsChartPart(value = 1),
    SpendingsChartPart(value = 2),
    SpendingsChartPart(value = 3),
    SpendingsChartPart(value = 4),
    SpendingsChartPart(value = 5),
)

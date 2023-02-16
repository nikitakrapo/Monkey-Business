package com.nikitakrapo.monkeybusiness.analytics.chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme

@Composable
fun LinearSpendingsChart(
    modifier: Modifier = Modifier,
    parts: List<SpendingsChartPart>,
) {
    val valueSum = remember(parts) {
        parts.sumOf(SpendingsChartPart::value)
    }
    Canvas(
        modifier = modifier
            .padding(12.dp),
    ) {
        drawLine(
            start = Offset(0f, 0f),
            end = Offset(0f, 0f),
            strokeWidth = 12.dp.toPx(),
            color = ChartColor.forIndex(0).color,
            cap = StrokeCap.Round
        )
        val lastEnd = size.width
        drawLine(
            start = Offset(lastEnd, 0f),
            end = Offset(lastEnd, 0f),
            strokeWidth = 12.dp.toPx(),
            color = ChartColor.forIndex(parts.lastIndex).color,
            cap = StrokeCap.Round
        )
        var x = 0f
        parts.forEachIndexed { index, spendingsChartPart ->
            val part = spendingsChartPart.value * size.width / valueSum
            drawLine(
                start = Offset(x, 0f),
                end = Offset(x + part, 0f),
                strokeWidth = 12.dp.toPx(),
                color = ChartColor.forIndex(index).color,
                cap = if (index == 0) StrokeCap.Round else StrokeCap.Butt
            )
            x += part
        }
    }
}

@Preview
@Composable
fun LinearSpendingsChart_Preview_Single() {
    MonkeyTheme {
        Surface {
            LinearSpendingsChart(
                modifier = Modifier.width(
                    width = 128.dp,
                ),
                parts = previewParts(1)
            )
        }
    }
}

@Preview
@Composable
fun LinearSpendingsChart_Preview_Two() {
    MonkeyTheme {
        Surface {
            LinearSpendingsChart(
                modifier = Modifier.width(
                    width = 128.dp,
                ),
                parts = previewParts(2)
            )
        }
    }
}

@Preview
@Composable
fun LinearSpendingsChart_Preview_Many() {
    MonkeyTheme {
        Surface {
            LinearSpendingsChart(
                modifier = Modifier.width(
                    width = 128.dp,
                ),
                parts = previewParts(5)
            )
        }
    }
}

private fun previewParts(number: Int) = buildList {
    for (i in 1..number) {
        add(SpendingsChartPart(value = i, name = ""))
    }
}
package com.nikitakrapo.monkeybusiness.analytics.chart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme

@Composable
fun ExpandableSpendingsChart(
    modifier: Modifier = Modifier,
    parts: List<SpendingsChartPart>,
    expanded: Boolean,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedVisibility(
            visible = expanded,
        ) {
            Column {
                Spacer(modifier = Modifier.height(24.dp))
                CircularSpendingsChart(
                    modifier = Modifier
                        .size(128.dp),
                    parts = parts
                )
            }
        }
        AnimatedVisibility(
            visible = !expanded,
        ) {
            LinearSpendingsChart(
                modifier = Modifier
                    .fillMaxWidth(),
                parts = parts
            )
        }
    }
}

private val fakeParts = listOf(
    SpendingsChartPart(value = 1),
    SpendingsChartPart(value = 2),
    SpendingsChartPart(value = 3),
    SpendingsChartPart(value = 4),
    SpendingsChartPart(value = 5),
)

@Preview(
    widthDp = 360,
    heightDp = 32
)
@Composable
fun ExpandableChart_Preview_Collapsed() {
    MonkeyTheme {
        Surface {
            ExpandableSpendingsChart(
                parts = fakeParts,
                expanded = false
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 156
)
@Composable
fun ExpandableChart_Preview_Expanded() {
    MonkeyTheme {
        Surface {
            ExpandableSpendingsChart(
                parts = fakeParts,
                expanded = true
            )
        }
    }
}



package com.nikitakrapo.monkeybusiness.analytics.chart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
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
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                CircularSpendingsChart(
                    modifier = Modifier
                        .size(128.dp),
                    parts = parts
                )
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    mainAxisSpacing = 8.dp,
                    crossAxisSpacing = 8.dp
                ) {
                    parts.forEachIndexed { index, part ->
                        SpendingChip(
                            part = part,
                            color = ChartColor.forIndex(index).color
                        )
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = !expanded,
        ) {
            LinearSpendingsChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
                parts = parts
            )
        }
    }
}

private val fakeParts = listOf(
    SpendingsChartPart(value = 1, name = "Fastfood"),
    SpendingsChartPart(value = 2, name = "Taxi"),
    SpendingsChartPart(value = 3, name = "House and repair"),
    SpendingsChartPart(value = 4, name = "Supermarkets"),
    SpendingsChartPart(value = 5, name = "Entertainment"),
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
    heightDp = 720
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



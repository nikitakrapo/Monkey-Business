package com.nikitakrapo.monkeybusiness.analytics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.analytics.chart.ExpandableSpendingsChart
import com.nikitakrapo.monkeybusiness.analytics.chart.SpendingsChartPart
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme

@Composable
fun AnalyticsScreen(
    modifier: Modifier = Modifier,
    component: AnalyticsComponent,
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .padding(horizontal = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SpendingsForTimeButton(onClick = {})
            Spacer(modifier = Modifier.weight(1f))
            SpendingsDropdownButton(
                expanded = expanded,
                onClick = { expanded = !expanded }
            )
        }
        ExpandableSpendingsChart(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            parts = fakeParts,
            expanded = expanded,
        )
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720
)
@Composable
fun AnalyticsScreen_Preview() {
    MonkeyTheme {
        Surface {
            AnalyticsScreen(component = PreviewAnalyticsComponent())
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


fun PreviewAnalyticsComponent() = object : AnalyticsComponent {

}
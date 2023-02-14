package com.nikitakrapo.monkeybusiness.analytics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.analytics.chart.SpendingsChart
import com.nikitakrapo.monkeybusiness.analytics.chart.SpendingsChartPart

@Composable
fun AnalyticsScreen(
    modifier: Modifier = Modifier,
    component: AnalyticsComponent,
) {
    Column(modifier = modifier) {
        SpendingsChart(
            modifier = Modifier
                .size(128.dp),
            parts = listOf(
                SpendingsChartPart(value = 1),
                SpendingsChartPart(value = 2),
                SpendingsChartPart(value = 3),
                SpendingsChartPart(value = 4),
                SpendingsChartPart(value = 5),
            )
        )
    }
}

fun PreviewAnalyticsComponent() = object : AnalyticsComponent {

}
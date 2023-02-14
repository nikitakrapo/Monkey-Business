package com.nikitakrapo.monkeybusiness.analytics.chart

data class SpendingsChartPart(
    val value: Int,
) {
    init {
        require(value > 0) { "value should be > 0" }
    }
}
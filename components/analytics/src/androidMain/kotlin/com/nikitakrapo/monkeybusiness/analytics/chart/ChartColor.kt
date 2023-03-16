package com.nikitakrapo.monkeybusiness.analytics.chart

import androidx.compose.ui.graphics.Color

enum class ChartColor(val color: Color) {
    Priority0(Color(0xFFFFB74D)), // Orange 300
    Priority1(Color(0xFFB39DDB)), // Deep Purple 200
    Priority2(Color(0xFF4DB6AC)), // Teal 300
    Priority3(Color(0xFFFFEB3B)), // Yellow 500
    Priority4(Color(0xFFE57373)), // Red 300
    Other(Color(0xFF4dd0e1)); // Cyan 300

    companion object {
        fun forIndex(index: Int) = when (index) {
            0 -> Priority0
            1 -> Priority1
            2 -> Priority2
            3 -> Priority3
            4 -> Priority4
            else -> Other
        }
    }
}
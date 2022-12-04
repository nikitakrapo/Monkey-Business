package com.nikitakrapo.monkeybusiness

import com.nikitakrapo.analytics.AnalyticsManager

class CoreScreenAnalytics(
    private val analytics: AnalyticsManager
) {

    fun onHomeClicked() {
        analytics.reportEvent(HOME_CLICKED)
    }

    fun onMoreClicked() {
        analytics.reportEvent(MORE_CLICKED)
    }

    private companion object {
        const val HOME_CLICKED = "Core.HomeClicked"
        const val MORE_CLICKED = "Core.MoreClicked"
    }
}

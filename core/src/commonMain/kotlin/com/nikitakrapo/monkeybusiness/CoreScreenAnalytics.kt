package com.nikitakrapo.monkeybusiness

import com.nikitakrapo.analytics.AnalyticsManager

class CoreScreenAnalytics(
    private val analytics: AnalyticsManager,
) {

    fun onHomeShown() {
        analytics.reportEvent(HOME_SHOWN)
    }

    fun onAuthenticationShown() {
        analytics.reportEvent(AUTHENTICATION_SHOWN)
    }

    private companion object {
        const val HOME_SHOWN = "core_home_shown"
        const val AUTHENTICATION_SHOWN = "core_authentication_shown"
    }
}

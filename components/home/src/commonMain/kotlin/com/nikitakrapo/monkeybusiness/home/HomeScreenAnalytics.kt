package com.nikitakrapo.monkeybusiness.home

import com.nikitakrapo.analytics.AnalyticsManager

class HomeScreenAnalytics(
    private val analyticsManager: AnalyticsManager,
) {

    fun onFinancesClicked() {
        analyticsManager.reportEvent(FINANCES_SHOWN)
    }

    fun onAnalyticsClicked() {
        analyticsManager.reportEvent(ANALYTICS_SHOWN)
    }

    fun onProfileClicked() {
        analyticsManager.reportEvent(PROFILE_SHOWN)
    }

    companion object {
        private const val FINANCES_SHOWN = "home_finances_shown"
        private const val ANALYTICS_SHOWN = "home_analytics_shown"
        private const val PROFILE_SHOWN = "home_profile_shown"
    }
}

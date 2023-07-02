package com.nikitakrapo.monkeybusiness

import com.nikitakrapo.analytics.AnalyticsManager

class CoreScreenAnalytics(
    private val analytics: AnalyticsManager,
) {

    fun onHomeShown() {
        analytics.reportEvent(HOME_SHOWN)
    }

    fun onBankAccountOpeningShown() {
        analytics.reportEvent(BANK_ACCOUNT_OPENING_SHOWN)
    }

    fun onAuthenticationShown() {
        analytics.reportEvent(AUTHENTICATION_SHOWN)
    }

    fun onSettingsShown() {
        analytics.reportEvent(SETTINGS_SHOWN)
    }

    private companion object {
        const val HOME_SHOWN = "core_home_shown"
        const val SETTINGS_SHOWN = "core_settings_shown"
        const val BANK_ACCOUNT_OPENING_SHOWN = "core_bank_account_opening_shown"
        const val AUTHENTICATION_SHOWN = "core_authentication_shown"
    }
}

package com.nikitakrapo.monkeybusiness.finances.opening

import com.nikitakrapo.analytics.AnalyticsManager

class ProductOpeningAnalytics(
    private val analyticsManager: AnalyticsManager
) {
    fun onOpenCardSelected() {
        analyticsManager.reportEvent(OPEN_CARD_SELECTED)
    }

    fun onOpenAccountSelected() {
        analyticsManager.reportEvent(OPEN_ACCOUNT_SELECTED)
    }

    companion object {
        const val OPEN_CARD_SELECTED = "product_opening_open_card_selected"
        const val OPEN_ACCOUNT_SELECTED = "product_opening_open_account_selected"
    }
}
package com.nikitakrapo.monkeybusiness.finances.products

import com.nikitakrapo.analytics.AnalyticsManager

class ProductOpeningDependencies(
    val analyticsManager: AnalyticsManager,
    val productOpeningRouter: ProductOpeningRouter,
)
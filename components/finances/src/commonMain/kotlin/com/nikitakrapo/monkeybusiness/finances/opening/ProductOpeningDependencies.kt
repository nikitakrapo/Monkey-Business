package com.nikitakrapo.monkeybusiness.finances.opening

import com.nikitakrapo.analytics.AnalyticsManager

class ProductOpeningDependencies(
    val analyticsManager: AnalyticsManager,
    val productOpeningRouter: ProductOpeningRouter,
)
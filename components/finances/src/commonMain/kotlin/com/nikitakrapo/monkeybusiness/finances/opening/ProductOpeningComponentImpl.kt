package com.nikitakrapo.monkeybusiness.finances.opening

import com.arkivanov.decompose.ComponentContext

class ProductOpeningComponentImpl(
    componentContext: ComponentContext,
    dependencies: ProductOpeningDependencies,
    private val openCardOpening: () -> Unit,
    private val openAccountOpening: () -> Unit,
) : ProductOpeningComponent, ComponentContext by componentContext {

    private val analytics = ProductOpeningAnalytics(dependencies.analyticsManager)

    override fun onOpenCardSelected() {
        analytics.onOpenCardSelected()
        openCardOpening()
    }

    override fun onOpenAccountSelected() {
        analytics.onOpenAccountSelected()
        openAccountOpening()
    }
}
package com.nikitakrapo.monkeybusiness.finances.products

import com.arkivanov.decompose.ComponentContext

class ProductOpeningComponentImpl(
    componentContext: ComponentContext,
    dependencies: ProductOpeningDependencies,
) : ProductOpeningComponent, ComponentContext by componentContext {

    private val analytics = ProductOpeningAnalytics(dependencies.analyticsManager)
    private val router = dependencies.productOpeningRouter

    override fun onOpenCardSelected() {
        analytics.onOpenCardSelected()
        router.openCardOpening()
    }

    override fun onOpenAccountSelected() {
        analytics.onOpenAccountSelected()
        router.openAccountOpening()
    }
}
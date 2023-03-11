package com.nikitakrapo.monkeybusiness.finances.accounts

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BankAccountsComponentImpl(
    componentContext: ComponentContext,
    dependencies: BankAccountsDependencies,
) : BankAccountsComponent, ComponentContext by componentContext {

    private val productOpeningLandingRouter = dependencies.productOpeningLandingRouter

    override val state: StateFlow<BankAccountsComponent.State> =
        MutableStateFlow(BankAccountsComponent.State(emptyList()))

    override fun onAccountClicked(index: Int) {
    }

    override fun onOpenProductClicked() {
        productOpeningLandingRouter.openProductOpening()
    }
}
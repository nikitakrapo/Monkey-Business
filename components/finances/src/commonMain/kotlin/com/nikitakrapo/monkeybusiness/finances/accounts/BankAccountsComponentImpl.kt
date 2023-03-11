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
        MutableStateFlow(BankAccountsComponent.State(fakeBankAccountList()))

    private fun fakeBankAccountList() = listOf(
        BankAccountViewState(
            name = "Main account",
            moneyText = "872 102,00 $",
            currencyText = "$",
            bankCardList = fakeSmallBankCardViewState(),
        )
    )

    private fun fakeSmallBankCardViewState() = listOf(
        SmallBankCardViewState("9924"),
        SmallBankCardViewState("4092"),
        SmallBankCardViewState("1928"),
    )

    override fun onAccountClicked(index: Int) {}

    override fun onOpenProductClicked() {
        productOpeningLandingRouter.openProductOpening()
    }
}
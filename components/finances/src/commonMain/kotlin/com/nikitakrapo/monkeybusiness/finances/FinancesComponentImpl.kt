package com.nikitakrapo.monkeybusiness.finances

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FinancesComponentImpl(
    componentContext: ComponentContext
) : FinancesComponent, ComponentContext by componentContext {
    private val stateFlow = MutableStateFlow(
        FinancesComponent.State(
            moneyAmount = MoneyAmount(
                amount = 123456,
                currency = Currency.GBP
            )
        )
    )
    override val state: StateFlow<FinancesComponent.State> get() = stateFlow.asStateFlow()

    override fun onDepositClicked() {
        changeMoneyAmount(1)
    }

    override fun onWithdrawClicked() {
        changeMoneyAmount(-1)
    }

    private fun changeMoneyAmount(delta: Int) {
        val currentAmount = state.value.moneyAmount
        val newAmount = currentAmount.copy(amount = currentAmount.amount + delta)
        stateFlow.value = FinancesComponent.State(
            moneyAmount = newAmount
        )
    }
}

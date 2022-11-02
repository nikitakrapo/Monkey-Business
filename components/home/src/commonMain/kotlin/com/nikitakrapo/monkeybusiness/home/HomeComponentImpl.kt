package com.nikitakrapo.monkeybusiness.home

import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeComponentImpl : HomeComponent {

    private val stateFlow = MutableStateFlow(
        HomeComponent.State(
            MoneyAmount(203141, Currency.GBP)
        )
    )
    override val state: StateFlow<HomeComponent.State> = stateFlow.asStateFlow()

    override fun onTopupClicked() {
        changeMoneyAmount(1)
    }

    override fun onWithdrawClicked() {
        changeMoneyAmount(-1)
    }

    private fun changeMoneyAmount(diff: Int) {
        stateFlow.value = state.value.copy(
            balance = state.value.balance.copy(
                amount = state.value.balance.amount + diff
            )
        )
    }
}
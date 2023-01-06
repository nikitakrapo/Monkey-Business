package com.nikitakrapo.monkeybusiness.finances

import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import kotlinx.coroutines.flow.StateFlow

interface FinancesComponent {

    val state: StateFlow<State>

    fun onDepositClicked()
    fun onWithdrawClicked()

    data class State(
        val moneyAmount: MoneyAmount,
    )
}

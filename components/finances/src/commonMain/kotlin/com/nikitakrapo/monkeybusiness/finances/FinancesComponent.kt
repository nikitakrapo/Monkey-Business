package com.nikitakrapo.monkeybusiness.finances

import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.finance.models.Transaction
import com.nikitakrapo.mvi.feature.Bootstrapper
import kotlinx.coroutines.flow.StateFlow

interface FinancesComponent {

    val state: StateFlow<State>

    fun onAddTransactionClicked()

    data class State(
        val moneyAmount: MoneyAmount,
        val transactionsList: List<Transaction>?,
        val transactionsLoading: Boolean,
    )
}

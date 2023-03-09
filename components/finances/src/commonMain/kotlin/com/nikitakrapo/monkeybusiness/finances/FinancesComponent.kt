package com.nikitakrapo.monkeybusiness.finances

import com.nikitakrapo.monkeybusiness.finances.accounts.BankAccountsComponent
import kotlinx.coroutines.flow.StateFlow

interface FinancesComponent {

    val state: StateFlow<State>

    val bankAccountsComponent: BankAccountsComponent

    fun refresh()

    data class State(
        val isRefreshing: Boolean,
    )
}

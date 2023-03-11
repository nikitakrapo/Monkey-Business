package com.nikitakrapo.monkeybusiness.finances

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.monkeybusiness.finances.FinancesComponent.State
import com.nikitakrapo.monkeybusiness.finances.accounts.BankAccountsComponent
import com.nikitakrapo.monkeybusiness.finances.accounts.BankAccountsComponentImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FinancesComponentImpl(
    componentContext: ComponentContext,
    dependencies: FinancesDependencies,
) : FinancesComponent, ComponentContext by componentContext {

    private val stateFlow = MutableStateFlow(State(isRefreshing = false))
    override val state: StateFlow<State> get() = stateFlow.asStateFlow()

    override val bankAccountsComponent: BankAccountsComponent = BankAccountsComponentImpl(
        componentContext = this,
        dependencies = dependencies.bankAccountsDependencies()
    )

    override fun refresh() {
        stateFlow.value = state.value.copy(isRefreshing = true)
    }
}

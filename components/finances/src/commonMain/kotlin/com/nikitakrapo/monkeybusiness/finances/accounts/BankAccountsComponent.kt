package com.nikitakrapo.monkeybusiness.finances.accounts

import kotlinx.coroutines.flow.StateFlow

interface BankAccountsComponent {

    val state: StateFlow<State>

    fun onAccountClicked(index: Int)
    fun onOpenAccountOrCardClicked()

    data class State(
        val accountList: List<BankAccountViewState>
    )
}
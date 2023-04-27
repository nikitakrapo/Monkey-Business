package com.nikitakrapo.monkeybusiness.finances.accounts

import com.nikitakrapo.monkeybusiness.finances.accounts.viewmodels.BankAccountViewState
import kotlinx.coroutines.flow.StateFlow

interface BankAccountsComponent {

    val state: StateFlow<State>

    fun onAccountClicked(index: Int)
    fun onOpenProductClicked()

    data class State(
        val accountList: List<BankAccountViewState>?,
        val showShimmer: Boolean,
    )
}
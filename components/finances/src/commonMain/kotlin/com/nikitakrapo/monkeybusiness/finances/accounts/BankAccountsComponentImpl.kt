package com.nikitakrapo.monkeybusiness.finances.accounts

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BankAccountsComponentImpl : BankAccountsComponent {
    override val state: StateFlow<BankAccountsComponent.State> =
        MutableStateFlow(BankAccountsComponent.State(emptyList()))

    override fun onAccountClicked(index: Int) {
    }

    override fun onOpenProductClicked() {
    }
}
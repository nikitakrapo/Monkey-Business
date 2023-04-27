package com.nikitakrapo.monkeybusiness.finances.accounts.viewmodel

sealed class BankAccountsScreenViewState {
    class ShowingAccounts(
        val accountList: List<BankAccountViewState>
    ) : BankAccountsScreenViewState()

    object Loading : BankAccountsScreenViewState()
}

package com.nikitakrapo.monkeybusiness.finances.accounts.viewmodels

data class BankAccountViewState(
    val name: String,
    val moneyText: String,
    val currencySign: String,
    val bankCardList: List<SmallBankCardViewState>
)
package com.nikitakrapo.monkeybusiness.finances.accounts.viewmodel

data class BankAccountViewState(
    val name: String,
    val moneyText: String,
    val currencySign: String,
    val bankCardList: List<SmallBankCardViewState>
)

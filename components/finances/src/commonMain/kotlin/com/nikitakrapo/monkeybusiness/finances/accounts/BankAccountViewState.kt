package com.nikitakrapo.monkeybusiness.finances.accounts

data class BankAccountViewState(
    val name: String,
    val moneyText: String,
    val currencyText: String,
    val bankCardList: List<SmallBankCardViewState>
)
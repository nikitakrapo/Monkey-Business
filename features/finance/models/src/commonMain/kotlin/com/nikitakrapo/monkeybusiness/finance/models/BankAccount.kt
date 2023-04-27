package com.nikitakrapo.monkeybusiness.finance.models

class BankAccount(
    val iban: String,
    val name: String,
    val balance: Long,
    val currency: Currency,
    val cards: List<BankCard>,
)
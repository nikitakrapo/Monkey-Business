package com.nikitakrapo.monkeybusiness.finance.models

import kotlinx.datetime.Instant

data class Spending(
    val id: String,
    val moneyAmount: MoneyAmount,
    val timestamp: Instant,
    val name: String
)
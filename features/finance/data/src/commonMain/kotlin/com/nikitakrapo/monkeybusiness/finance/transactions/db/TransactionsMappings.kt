package com.nikitakrapo.monkeybusiness.finance.transactions.db

import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.finance.models.Transaction
import finance.transactions.TransactionItem as TransactionModel

internal fun TransactionModel.mapToDomainModel() = mapToTransaction(
    id = id,
    amount = amount,
    currency = currency,
    timestamp = timestampMs,
    name = name,
)

internal fun mapToTransaction(
    id: String,
    amount: Double,
    currency: String,
    timestamp: Long,
    name: String,
): Transaction =
    Transaction(
        id = id,
        moneyAmount = MoneyAmount(amount = amount, currency = Currency.fromCode(currency)),
        timestampMs = timestamp,
        name = name,
    )

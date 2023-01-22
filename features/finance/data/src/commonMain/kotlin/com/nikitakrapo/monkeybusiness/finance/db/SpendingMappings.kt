package com.nikitakrapo.monkeybusiness.finance.db

import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.finance.models.Spending
import finance.spendings.Spending as SpendingModel
import kotlinx.datetime.Instant

internal fun SpendingModel.mapToDomainModel() = mapToSpending(
    id = id,
    amount = amount,
    currency = currency,
    timestamp = timestamp,
    description = description,
)

internal fun mapToSpending(
    id: String,
    amount: Long,
    currency: String,
    timestamp: Long,
    description: String?,
): Spending =
    Spending(
        id = id,
        moneyAmount = MoneyAmount(amount = amount, currency = Currency.fromCode(currency)),
        timestamp = Instant.Companion.fromEpochMilliseconds(timestamp),
        description = description,
    )

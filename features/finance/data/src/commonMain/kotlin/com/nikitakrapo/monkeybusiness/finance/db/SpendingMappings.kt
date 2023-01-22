package com.nikitakrapo.monkeybusiness.finance.db

import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.finance.models.Spending
import kotlinx.datetime.Instant

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

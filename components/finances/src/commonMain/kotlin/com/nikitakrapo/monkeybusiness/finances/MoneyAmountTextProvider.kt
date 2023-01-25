package com.nikitakrapo.monkeybusiness.finances

import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import kotlin.math.abs

object MoneyAmountTextProvider {
    fun MoneyAmount.createText(): MoneyAmountText {
        val sign = if (amount > 0) '+' else '-'
        val unsignedAmount = abs(amount).toString()
        val currency = currency.symbol
        return MoneyAmountText(
            sign = sign,
            amount = unsignedAmount,
            currency = currency,
        )
    }
}

data class MoneyAmountText(
    val sign: Char,
    val amount: String,
    val currency: String,
) {
    fun asString(skipPositiveSign: Boolean = false) = buildString {
        if (!skipPositiveSign || sign != '+') {
            append("$sign ")
        }
        append("$amount ")
        append(currency)
    }
}

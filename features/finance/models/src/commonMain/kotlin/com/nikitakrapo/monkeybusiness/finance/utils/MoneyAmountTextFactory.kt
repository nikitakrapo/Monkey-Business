package com.nikitakrapo.monkeybusiness.finance.utils

import com.nikitakrapo.monkeybusiness.finance.models.Currency

object MoneyAmountTextFactory {

    private const val THOUSAND_DIGITS_NUM = 3

    /**
     * @param currency balance currency
     * @param amount amount in minor unit
     */
    fun createMoneyAmountText(
        currency: Currency,
        amount: Long,
    ): String {
        val paddedAmount = amount.toString().padStart(
            length = currency.minorUnitDecimals + 1,
            padChar = '0'
        )
        return buildString {
            append(paddedAmount)
            val commaInsertIndex = length - currency.minorUnitDecimals
            insert(commaInsertIndex, ',')

            var thousandsPointer = commaInsertIndex - THOUSAND_DIGITS_NUM
            while (thousandsPointer > 0) {
                insert(thousandsPointer, ' ')
                thousandsPointer -= THOUSAND_DIGITS_NUM
            }

            append(" ${currency.symbol}")
        }
    }
}
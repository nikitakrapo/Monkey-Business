package com.nikitakrapo.monkeybusiness.finances

import android.content.Context
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.resources.R

object CurrencyNameProvider {
    fun Currency.getFullName(context: Context) = when(this) {
        Currency.USD -> context.getString(R.string.currency_name_usd)
        Currency.EUR -> context.getString(R.string.currency_name_eur)
        Currency.GBP -> context.getString(R.string.currency_name_gbp)
        Currency.RUB -> context.getString(R.string.currency_name_rub)
        Currency.HUF -> context.getString(R.string.currency_name_huf)
    }
}
package com.nikitakrapo.monkeybusiness.finance.utils

import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.utils.MoneyAmountTextFactory.createMoneyAmountText
import kotlin.test.Test
import kotlin.test.assertEquals

class MoneyAmountTextFactoryTests {

    @Test
    fun one_hundred_dollars() {
        val actualText = createMoneyAmountText(
            currency = Currency.USD,
            amount = 100_00
        )
        assertEquals("100,00 $", actualText)
    }

    @Test
    fun one_thousand_dollars() {
        val actualText = createMoneyAmountText(
            currency = Currency.USD,
            amount = 1_000_00
        )
        assertEquals("1 000,00 $", actualText)
    }

    @Test
    fun one_million_dollars() {
        val actualText = createMoneyAmountText(
            currency = Currency.USD,
            amount = 1_000_000_00
        )
        assertEquals("1 000 000,00 $", actualText)
    }

    @Test
    fun fifty_cent() {
        val actualText = createMoneyAmountText(
            currency = Currency.USD,
            amount = 50
        )
        assertEquals("0,50 $", actualText)
    }

    @Test
    fun fifty_dollars_fifty_cents() {
        val actualText = createMoneyAmountText(
            currency = Currency.USD,
            amount = 50_50
        )
        assertEquals("50,50 $", actualText)
    }

    @Test
    fun one_thousand_rubles() {
        val actualText = createMoneyAmountText(
            currency = Currency.RUB,
            amount = 1_000_00
        )
        assertEquals("1 000,00 ₽", actualText)
    }
}
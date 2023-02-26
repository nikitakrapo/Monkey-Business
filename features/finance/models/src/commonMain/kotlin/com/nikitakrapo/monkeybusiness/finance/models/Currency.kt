package com.nikitakrapo.monkeybusiness.finance.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property code Currency code in ISO 4217
 */
@Serializable
enum class Currency(
    @SerialName("code")
    val code: String,
    @SerialName("symbol")
    val symbol: String,
) {
    USD("USD", "$"),
    EUR("EUR", "€"),
    GBP("GBP", "£"),
    RUB("RUB", "₽"),
    HUF("HUF", "Ft");

    companion object {
        fun fromCode(code: String): Currency {
            val currency = Currency.values().firstOrNull {
                it.code == code
            } ?: throw IllegalArgumentException("No Currency with code $code")
            return currency
        }
    }
}

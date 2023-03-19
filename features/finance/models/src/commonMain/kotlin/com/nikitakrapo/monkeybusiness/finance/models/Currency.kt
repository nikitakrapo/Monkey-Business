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
    @SerialName("minorUnitDecimals")
    val minorUnitDecimals: Int,
) {
    USD("USD", "$", 2),
    EUR("EUR", "€", 2),
    GBP("GBP", "£", 2),
    RUB("RUB", "₽", 2),
    HUF("HUF", "Ft", 2);

    companion object {
        fun fromCode(code: String): Currency {
            val currency = Currency.values().firstOrNull {
                it.code == code
            } ?: throw IllegalArgumentException("No Currency with code $code")
            return currency
        }
    }
}

package com.nikitakrapo.monkeybusiness.finance.models

/**
 * @property code Currency code in ISO 4217
 */
enum class Currency(
    val code: String,
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

package com.nikitakrapo.monkeybusiness.finance.models

/**
 * @property code Currency code in ISO 4217
 */
enum class Currency(val code: String) {
    USD("USD"),
    EUR("EUR"),
    GBP("GBP"),
    RUB("RUB"),
    HUF("HUF")
}

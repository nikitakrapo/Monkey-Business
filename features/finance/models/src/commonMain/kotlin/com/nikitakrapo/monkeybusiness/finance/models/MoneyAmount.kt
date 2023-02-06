package com.nikitakrapo.monkeybusiness.finance.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO: move to shared BE & Mobile module
@Serializable
data class MoneyAmount(
    @SerialName("amount")
    val amount: Double,
    @SerialName("currency")
    val currency: Currency,
)

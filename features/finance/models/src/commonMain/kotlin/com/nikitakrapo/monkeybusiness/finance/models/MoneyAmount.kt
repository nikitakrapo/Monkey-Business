package com.nikitakrapo.monkeybusiness.finance.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO: move to shared BE & Mobile module
@Serializable
data class MoneyAmount(
    @SerialName("amount")
    val amount: Long,
    @SerialName("currency")
    val currency: Currency,
)

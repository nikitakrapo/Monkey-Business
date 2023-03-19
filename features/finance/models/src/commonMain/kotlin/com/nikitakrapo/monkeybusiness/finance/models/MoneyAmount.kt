package com.nikitakrapo.monkeybusiness.finance.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Deprecated("It's far better to use currency & minor unit amount separately")
@Serializable
data class MoneyAmount(
    @SerialName("amount")
    val amount: Double,
    @SerialName("currency")
    val currency: Currency,
)

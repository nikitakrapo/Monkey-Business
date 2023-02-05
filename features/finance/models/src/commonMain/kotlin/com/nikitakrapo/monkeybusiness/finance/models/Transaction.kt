package com.nikitakrapo.monkeybusiness.finance.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO: move to shared backend & mobile module
// TODO: form id & timestamp on backend
@Serializable
data class Transaction(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("moneyAmount")
    val moneyAmount: MoneyAmount,
    @SerialName("timestampMs")
    val timestampMs: Long,
)

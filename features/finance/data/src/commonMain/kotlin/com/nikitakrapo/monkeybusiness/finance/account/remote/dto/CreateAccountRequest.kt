package com.nikitakrapo.monkeybusiness.finance.account.remote.dto

import com.nikitakrapo.monkeybusiness.finance.models.Currency
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateAccountRequest(
    @SerialName("currency")
    val currency: Currency,
)
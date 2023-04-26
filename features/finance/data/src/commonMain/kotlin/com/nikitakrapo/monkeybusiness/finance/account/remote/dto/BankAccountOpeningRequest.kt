package com.nikitakrapo.monkeybusiness.finance.account.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BankAccountOpeningRequest(
    @SerialName("currencyCode")
    val currencyCode: String,
)
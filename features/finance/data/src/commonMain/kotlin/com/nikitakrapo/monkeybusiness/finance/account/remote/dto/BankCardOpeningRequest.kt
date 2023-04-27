package com.nikitakrapo.monkeybusiness.finance.account.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class BankCardOpeningRequest(
    @SerialName("iban")
    val iban: String,
)

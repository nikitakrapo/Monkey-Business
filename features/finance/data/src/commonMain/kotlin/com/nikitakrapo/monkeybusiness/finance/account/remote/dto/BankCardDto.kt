package com.nikitakrapo.monkeybusiness.finance.account.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class BankCardDto(
    @SerialName("pan")
    val pan: String,
)

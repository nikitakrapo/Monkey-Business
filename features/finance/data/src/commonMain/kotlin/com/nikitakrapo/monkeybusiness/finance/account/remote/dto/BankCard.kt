package com.nikitakrapo.monkeybusiness.finance.account.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BankCard(
    @SerialName("pan")
    val pan: String,
)

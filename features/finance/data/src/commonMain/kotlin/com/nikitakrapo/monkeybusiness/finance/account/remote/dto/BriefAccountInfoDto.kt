package com.nikitakrapo.monkeybusiness.finance.account.remote.dto

import com.nikitakrapo.monkeybusiness.finance.models.Currency
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BriefAccountInfoDto(
    @SerialName("name")
    val name: String,
    @SerialName("currency")
    val currency: Currency,
    @SerialName("balance")
    val balance: Long,
)
package com.nikitakrapo.monkeybusiness.finance.account.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateAccountResponse(
    @SerialName("isAccountCreated")
    val isAccountCreated: Boolean,
)
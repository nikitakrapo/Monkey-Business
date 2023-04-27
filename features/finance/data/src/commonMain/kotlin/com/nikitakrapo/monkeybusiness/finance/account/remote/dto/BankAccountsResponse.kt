package com.nikitakrapo.monkeybusiness.finance.account.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class BankAccountsResponse(
    @SerialName("accounts")
    val accounts: List<BankAccountDto>,
)

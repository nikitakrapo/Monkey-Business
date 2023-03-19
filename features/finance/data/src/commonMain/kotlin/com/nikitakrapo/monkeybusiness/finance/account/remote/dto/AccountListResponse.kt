package com.nikitakrapo.monkeybusiness.finance.account.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountListResponse(
    @SerialName("accountList")
    val accountList: List<BriefAccountInfo>,
)
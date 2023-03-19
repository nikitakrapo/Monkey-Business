package com.nikitakrapo.monkeybusiness.finance.models

data class BriefAccountInfo(
    val name: String,
    val currency: Currency,
    val balance: Long,
)
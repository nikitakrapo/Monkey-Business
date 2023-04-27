package com.nikitakrapo.monkeybusiness.finance.account.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class BankAccountDto(
    @SerialName("iban")
    val iban: String,
    @SerialName("balance")
    val balance: Long,
    @SerialName("currencyCode")
    val currencyCode: String,
    @SerialName("cards")
    val cards: List<BankCardDto>,
    @SerialName("name")
    val name: String,
)

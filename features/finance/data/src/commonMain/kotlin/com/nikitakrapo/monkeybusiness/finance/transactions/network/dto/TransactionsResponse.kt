package com.nikitakrapo.monkeybusiness.finance.transactions.network.dto

import com.nikitakrapo.monkeybusiness.finance.models.Transaction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class TransactionsResponse(
    @SerialName("transactionList")
    val transactionList: List<Transaction>,
)

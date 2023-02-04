package com.nikitakrapo.monkeybusiness.finance

import com.nikitakrapo.monkeybusiness.finance.models.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {
    fun addTransaction(transaction: Transaction)
    fun getAllTransactions(): Flow<List<Transaction>>
}
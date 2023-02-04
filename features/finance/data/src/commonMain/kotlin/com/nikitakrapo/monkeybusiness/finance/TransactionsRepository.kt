package com.nikitakrapo.monkeybusiness.finance

import com.nikitakrapo.monkeybusiness.finance.models.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {
    suspend fun addTransaction(transaction: Transaction)
    suspend fun getAllTransactions(): Flow<List<Transaction>>
}
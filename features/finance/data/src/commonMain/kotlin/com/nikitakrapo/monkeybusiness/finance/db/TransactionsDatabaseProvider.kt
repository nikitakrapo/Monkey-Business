package com.nikitakrapo.monkeybusiness.finance.db

import com.nikitakrapo.monkeybusiness.db.DatabaseDriverFactory
import com.nikitakrapo.monkeybusiness.db.asFlow
import com.nikitakrapo.monkeybusiness.db.mapToList
import com.nikitakrapo.monkeybusiness.finance.models.Transaction
import finance.transactions.TransactionsDatabase
import kotlinx.coroutines.flow.Flow

internal class TransactionsDatabaseProvider(
    databaseDriverFactory: DatabaseDriverFactory,
) {
    private val transactionsDatabase = TransactionsDatabase(
        databaseDriverFactory.createDriver(
            schema = TransactionsDatabase.Schema,
            name = "transactions.db"
        )
    )
    private val queries = transactionsDatabase.transactionsDatabaseQueries

    fun addTransaction(transaction: Transaction) {
        queries.insertTransaction(
            id = transaction.id,
            amount = transaction.moneyAmount.amount,
            currency = transaction.moneyAmount.currency.code,
            timestampMs = transaction.timestampMs,
            name = transaction.name,
        )
    }

    fun getTransactionById(id: String): Transaction? {
        return queries.selectTransactionById(id).executeAsOneOrNull()?.mapToDomainModel()
    }

    fun getAllTransactions(): List<Transaction> {
        return queries.selectAllTransactions(::mapToTransaction).executeAsList()
    }

    fun getAllTransactionsFlow(): Flow<List<Transaction>> {
        return queries.selectAllTransactions(::mapToTransaction)
            .asFlow()
            .mapToList()
    }

    fun removeTransaction(id: String) {
        queries.removeTransaction(id)
    }

    internal fun removeAllTransactions() {
        queries.removeAllTransactions()
    }
}
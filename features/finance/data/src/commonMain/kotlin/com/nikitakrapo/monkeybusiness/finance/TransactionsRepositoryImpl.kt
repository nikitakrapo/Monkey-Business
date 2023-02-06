package com.nikitakrapo.monkeybusiness.finance

import com.nikitakrapo.application.PlatformContext
import com.nikitakrapo.monkeybusiness.db.DatabaseDriverFactory
import com.nikitakrapo.monkeybusiness.finance.db.TransactionsDatabaseProvider
import com.nikitakrapo.monkeybusiness.finance.models.Transaction
import com.nikitakrapo.monkeybusiness.finance.network.TransactionsApi
import com.nikitakrapo.monkeybusiness.finance.network.dto.TransactionRequest
import com.nikitakrapo.monkeybusiness.network.auth.BearerTokensProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart

class TransactionsRepositoryImpl(
    platformContext: PlatformContext,
    bearerTokensProvider: BearerTokensProvider,
) : TransactionsRepository {

    private val db: TransactionsDatabaseProvider = TransactionsDatabaseProvider(
        databaseDriverFactory = DatabaseDriverFactory(platformContext = platformContext),
    )

    private val api = TransactionsApi(
        bearerTokensProvider = bearerTokensProvider,
    )

    override suspend fun addTransaction(transaction: Transaction) {
        val request = TransactionRequest(transaction = transaction)
        api.postTransaction(request)
        db.addTransaction(transaction)
    }

    override suspend fun getAllTransactions(): Flow<List<Transaction>> =
        db.getAllTransactionsFlow()
            .onStart {
                val remoteTransactions = api.getAllTransactions().transactionList
                if (db.getAllTransactions() != remoteTransactions) {
                    db.updateTransactions(remoteTransactions)
                }
                emit(remoteTransactions)
            }
            .distinctUntilChanged()
}

package com.nikitakrapo.monkeybusiness.finance.db

import com.nikitakrapo.monkeybusiness.finance.models.Spending
import com.nikitakrapo.monkeybusiness.finance.db.mapToList
import finance.spendings.SpendingsDatabase
import kotlinx.coroutines.flow.Flow

class SpendingsDatabaseProvider(
    databaseDriverFactory: DatabaseDriverFactory,
) {
    private val spendingsDatabase = SpendingsDatabase(databaseDriverFactory.createDriver())
    private val queries = spendingsDatabase.spendingsDatabaseQueries

    fun addSpending(spending: Spending) {
        queries.insertSpending(
            id = spending.id,
            amount = spending.moneyAmount.amount,
            currency = spending.moneyAmount.currency.code,
            timestamp = spending.timestamp.toEpochMilliseconds(),
            description = spending.description,
        )
    }

    fun getAllSpendingsFlow(): Flow<List<Spending>> {
        return queries.selectAllSpendings(::mapToSpending)
            .asFlow()
            .mapToList()
    }

    fun removeSpending(id: String) {
        queries.removeSpending(id)
    }

    internal fun removeAllSpendings() {
        queries.removeAllSpendings()
    }
}
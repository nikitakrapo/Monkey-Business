package com.nikitakrapo.monkeybusiness.finance.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nikitakrapo.monkeybusiness.db.DatabaseDriverFactory
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.finance.models.Transaction
import kotlinx.datetime.Instant
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class TransactionsDatabaseProviderTests {

    private lateinit var dbProvider: TransactionsDatabaseProvider

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val driverFactory = DatabaseDriverFactory(context = context)
        dbProvider = TransactionsDatabaseProvider(databaseDriverFactory = driverFactory)
    }

    @Test
    fun initialEmpty() {
        val transactions = dbProvider.getAllTransactions()
        assert(transactions.isEmpty()) { "Non empty initial transactions" }
    }

    @Test
    fun correctTransaction_whenInserted() {
        val transaction = transaction()

        dbProvider.addTransaction(transaction)

        assertEquals(transaction, dbProvider.getTransactionById(id = transaction.id))
    }

    @Test
    fun emptyTransaction_whenDeleted() {
        val transaction = transaction()

        dbProvider.addTransaction(transaction)

        dbProvider.removeTransaction(id = transaction.id)

        assertEquals(null, dbProvider.getTransactionById(id = transaction.id))
    }

    @Test
    fun correctTransactions_whenInserted() {
        val transaction1 = transaction(id = "sp1")
        val transaction2 = transaction(id = "sp2")
        val transaction3 = transaction(id = "sp3")

        dbProvider.addTransaction(transaction1)
        dbProvider.addTransaction(transaction2)
        dbProvider.addTransaction(transaction3)

        val expectedTransactions = listOf(transaction1, transaction2, transaction3)

        val transactions = dbProvider.getAllTransactions()
        assertEquals(transactions, expectedTransactions)
    }

    @After
    fun teardown() {
        dbProvider.removeAllTransactions()
    }

    private fun transaction(
        id: String = "uuid",
    ) = Transaction(
        id = id,
        moneyAmount = MoneyAmount(amount = 1000, currency = Currency.HUF),
        timestamp = Instant.fromEpochSeconds(1674415617),
        name = "sample desc",
    )
}
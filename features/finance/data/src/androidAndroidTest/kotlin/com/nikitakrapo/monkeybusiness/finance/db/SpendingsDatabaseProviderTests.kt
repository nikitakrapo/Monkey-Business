package com.nikitakrapo.monkeybusiness.finance.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.finance.models.Spending
import kotlinx.datetime.Instant
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class SpendingsDatabaseProviderTests {

    private lateinit var dbProvider: SpendingsDatabaseProvider

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val driverFactory = DatabaseDriverFactory(context = context)
        dbProvider = SpendingsDatabaseProvider(databaseDriverFactory = driverFactory)
    }

    @Test
    fun initialEmpty() {
        val spendings = dbProvider.getAllSpendings()
        assert(spendings.isEmpty()) { "Non empty initial spendings" }
    }

    @Test
    fun correctSpending_whenInserted() {
        val spending = spending()

        dbProvider.addSpending(spending)

        assertEquals(spending, dbProvider.getSpendingById(id = spending.id))
    }

    @Test
    fun emptySpending_whenDeleted() {
        val spending = spending()

        dbProvider.addSpending(spending)

        dbProvider.removeSpending(id = spending.id)

        assertEquals(null, dbProvider.getSpendingById(id = spending.id))
    }

    @Test
    fun correctSpendings_whenInserted() {
        val spending1 = spending(id = "sp1")
        val spending2 = spending(id = "sp2")
        val spending3 = spending(id = "sp3")

        dbProvider.addSpending(spending1)
        dbProvider.addSpending(spending2)
        dbProvider.addSpending(spending3)

        val expectedSpendings = listOf(spending1, spending2, spending3)

        val spendings = dbProvider.getAllSpendings()
        assertEquals(spendings, expectedSpendings)
    }

    @After
    fun teardown() {
        dbProvider.removeAllSpendings()
    }

    private fun spending(
        id: String = "uuid",
    ) = Spending(
        id = id,
        moneyAmount = MoneyAmount(amount = 1000, currency = Currency.HUF),
        timestamp = Instant.fromEpochSeconds(1674415617),
        name = "sample desc",
    )
}
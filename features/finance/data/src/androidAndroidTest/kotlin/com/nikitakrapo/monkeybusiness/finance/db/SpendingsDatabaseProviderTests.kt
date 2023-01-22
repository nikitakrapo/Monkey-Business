package com.nikitakrapo.monkeybusiness.finance.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.cash.turbine.test
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.finance.models.Spending
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
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
    fun initialEmpty() = runTest {
        dbProvider.getAllSpendingsFlow().test {
            assert(awaitItem().isEmpty()) { "Non empty initial spendings" }
        }
    }

    @Test
    fun emitsSpendings_whenInserted() = runTest {
        val spending1 = spending(id = "sp1")
        val spending2 = spending(id = "sp2")

        dbProvider.addSpending(spending1)
        dbProvider.addSpending(spending2)

        dbProvider.getAllSpendingsFlow().test {
            assertEquals(awaitItem(), listOf(spending1, spending2))
        }
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
        description = "sample desc",
    )
}
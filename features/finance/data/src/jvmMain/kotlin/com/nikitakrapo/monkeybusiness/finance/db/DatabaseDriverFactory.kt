package com.nikitakrapo.monkeybusiness.finance.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import finance.transactions.TransactionsDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        TransactionsDatabase.Schema.create(driver)
        return driver
    }
}
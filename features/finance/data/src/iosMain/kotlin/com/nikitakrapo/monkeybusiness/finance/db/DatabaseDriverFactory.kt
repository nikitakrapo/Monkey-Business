package com.nikitakrapo.monkeybusiness.finance.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import finance.spendings.SpendingsDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(SpendingsDatabase.Schema, "spendings.db")
    }
}

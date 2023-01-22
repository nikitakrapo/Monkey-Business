package com.nikitakrapo.monkeybusiness.finance.db

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import finance.spendings.SpendingsDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(SpendingsDatabase.Schema, context, "spendings.db")
    }
}

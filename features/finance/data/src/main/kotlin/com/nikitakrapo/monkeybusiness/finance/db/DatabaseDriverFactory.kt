package com.nikitakrapo.monkeybusiness.finance.db

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import finance.transactions.TransactionsDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(TransactionsDatabase.Schema, context, "transactions.db")
    }
}

package com.nikitakrapo.monkeybusiness.db

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(
        schema: SqlDriver.Schema,
        name: String
    ): SqlDriver {
        return AndroidSqliteDriver(schema, context, name)
    }
}

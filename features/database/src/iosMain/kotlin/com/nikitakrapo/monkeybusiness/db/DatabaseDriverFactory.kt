package com.nikitakrapo.monkeybusiness.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(
        schema: SqlDriver.Schema,
        name: String
    ): SqlDriver {
        return NativeSqliteDriver(schema, name)
    }
}

package com.nikitakrapo.monkeybusiness.db

import com.nikitakrapo.application.PlatformContext
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory actual constructor(
    private val platformContext: PlatformContext,
) {
    actual fun createDriver(
        schema: SqlDriver.Schema,
        name: String,
    ): SqlDriver {
        return AndroidSqliteDriver(schema, platformContext.context, name)
    }
}

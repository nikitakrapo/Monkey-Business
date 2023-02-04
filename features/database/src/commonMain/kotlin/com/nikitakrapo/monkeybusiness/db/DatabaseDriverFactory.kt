package com.nikitakrapo.monkeybusiness.db

import com.nikitakrapo.application.PlatformContext
import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory constructor(
    platformContext: PlatformContext
) {
    /**
     * @param name Name for database file
     */
    fun createDriver(
        schema: SqlDriver.Schema,
        name: String
    ): SqlDriver
}

package com.nikitakrapo.monkeybusiness.db

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    /**
     * @param name Name for database file
     */
    fun createDriver(
        schema: SqlDriver.Schema,
        name: String
    ): SqlDriver
}

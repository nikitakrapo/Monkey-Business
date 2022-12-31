package com.nikitakrapo.account

import kotlinx.coroutines.flow.StateFlow

interface AccountManager {
    val currentAccount: StateFlow<Account?>

    suspend fun createAccount(
        email: String,
        password: String,
        username: String
    ): Result<Account>

    suspend fun login(
        email: String,
        password: String
    ): Result<Account>

    fun logout(): Result<Unit>
}

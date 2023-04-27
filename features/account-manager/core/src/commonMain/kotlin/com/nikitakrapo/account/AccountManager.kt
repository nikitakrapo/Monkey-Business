package com.nikitakrapo.account

import com.nikitakrapo.account.models.Account
import com.nikitakrapo.account.models.AccountUpdateRequest
import kotlinx.coroutines.flow.StateFlow

interface AccountManager {

    val account: StateFlow<Account?>

    suspend fun getToken(): Result<String?>

    suspend fun createAccount(
        email: String,
        password: String,
    ): Result<Account>

    suspend fun login(
        email: String,
        password: String,
    ): Result<Account>

    fun logout(): Result<Boolean>

    suspend fun updateAccount(
        configure: AccountUpdateRequest.() -> Unit,
    ): Result<Unit>
}

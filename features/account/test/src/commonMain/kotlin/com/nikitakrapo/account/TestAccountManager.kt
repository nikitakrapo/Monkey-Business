package com.nikitakrapo.account

import com.nikitakrapo.account.models.Account
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

fun testAccountManager(
    defaultAccount: Account? = null,
) = object : AccountManager {

    private val accountFlow = MutableStateFlow(defaultAccount)
    override val account: StateFlow<Account?> get() = accountFlow.asStateFlow()

    override suspend fun createAccount(
        email: String,
        password: String,
        username: String
    ): Result<Account> {
        val newAccount = Account.Emailish(
            uid = "uid",
            email = "sample@email.com"
        )
        accountFlow.value = newAccount
        return Result.success(newAccount)
    }

    override suspend fun login(email: String, password: String): Result<Account> {
        val newAccount = Account.Emailish(
            uid = "uid",
            email = email
        )
        accountFlow.value = newAccount
        return Result.success(newAccount)
    }

    override fun logout(): Result<Unit> {
        accountFlow.value = null
        return Result.success(Unit)
    }
}

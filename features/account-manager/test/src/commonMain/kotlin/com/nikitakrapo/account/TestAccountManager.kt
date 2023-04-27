package com.nikitakrapo.account

import com.nikitakrapo.account.models.Account
import com.nikitakrapo.account.models.AccountUpdateRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

fun testAccountManager(
    defaultAccount: Account? = null,
) = object : AccountManager {

    private val accountFlow = MutableStateFlow(defaultAccount)
    override val account: StateFlow<Account?> get() = accountFlow.asStateFlow()

    override suspend fun getToken(): Result<String?> {
        return Result.success("")
    }

    override suspend fun createAccount(
        email: String,
        password: String,
    ): Result<Account> {
        val newAccount = Account(
            uid = "uid",
            email = "sample@email.com",
            username = null,
            photoUrl = null,
        )
        accountFlow.value = newAccount
        return Result.success(newAccount)
    }

    override suspend fun login(
        email: String,
        password: String,
    ): Result<Account> {
        val newAccount = Account(
            uid = "uid",
            email = email,
            username = null,
            photoUrl = null,
        )
        accountFlow.value = newAccount
        return Result.success(newAccount)
    }

    override fun logout(): Result<Boolean> {
        accountFlow.value = null
        return Result.success(true)
    }

    override suspend fun updateAccount(
        configure: AccountUpdateRequest.() -> Unit,
    ): Result<Unit> {
        val request = AccountUpdateRequest().apply(configure)
        accountFlow.value = accountFlow.value?.copy(username = request.username)
        return Result.success(Unit)
    }
}

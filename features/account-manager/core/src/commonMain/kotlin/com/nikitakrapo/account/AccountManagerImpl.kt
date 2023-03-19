package com.nikitakrapo.account

import com.nikitakrapo.account.firebase.FirebaseAuthProvider
import com.nikitakrapo.account.models.Account
import com.nikitakrapo.account.models.AccountUpdateRequest
import com.nikitakrapo.analytics.AnalyticsManager
import kotlinx.coroutines.flow.StateFlow

// TODO: handle exceptions properly
class AccountManagerImpl(
    analyticsManager: AnalyticsManager,
) : AccountManager {

    private val analytics = AccountManagerAnalytics(analyticsManager)

    private val authProvider by lazy { FirebaseAuthProvider }

    override val account: StateFlow<Account?> get() = authProvider.account

    override suspend fun getToken(): Result<String?> {
        return try {
            val token = authProvider.getIdToken()
            Result.success(token)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(email: String, password: String): Result<Account> {
        return try {
            analytics.onLoginStarted(email)
            val account = authProvider.signInWithEmailAndPassword(
                email = email,
                password = password,
            ) ?: throw IllegalStateException()
            analytics.onLoginSucceeded()
            Result.success(account)
        } catch (e: Exception) {
            analytics.onLoginFailed(e.message)
            Result.failure(e)
        }
    }

    override suspend fun createAccount(
        email: String,
        password: String,
        username: String,
    ): Result<Account> {
        return try {
            analytics.onCreateAccountStarted(email)
            val account = authProvider.createUserWithEmailAndPassword(
                email = email,
                password = password,
            ) ?: throw IllegalStateException()
            analytics.onCreateAccountStarted(email)
            Result.success(account)
        } catch (e: Exception) {
            analytics.onCreateAccountFailed(e.message)
            Result.failure(e)
        }
    }

    override fun logout(): Result<Boolean> {
        return try {
            analytics.onLogoutStarted()
            val result = authProvider.signOut()
            analytics.onLogoutSucceeded()
            Result.success(result)
        } catch (e: Exception) {
            analytics.onLogoutFailed(e.message)
            Result.failure(e)
        }
    }

    override suspend fun updateAccount(
        configure: AccountUpdateRequest.() -> Unit,
    ): Result<Unit> {
        val request = AccountUpdateRequest().apply(configure)
        return Result.runCatching {
            authProvider.updateAccount(request)
        }
    }
}

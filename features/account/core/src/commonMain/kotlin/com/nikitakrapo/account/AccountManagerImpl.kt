package com.nikitakrapo.account

import com.nikitakrapo.account.firebase.FirebaseAuthProvider
import com.nikitakrapo.account.models.Account
import kotlinx.coroutines.flow.StateFlow

//TODO: handle exceptions properly
class AccountManagerImpl : AccountManager {

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
            val account = authProvider.signInWithEmailAndPassword(
                email = email,
                password = password
            ) ?: throw IllegalStateException()
            Result.success(account)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createAccount(
        email: String,
        password: String,
        username: String
    ): Result<Account> {
        return try {
            val account = authProvider.createUserWithEmailAndPassword(
                email = email,
                password = password
            ) ?: throw IllegalStateException()
            Result.success(account)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun logout(): Result<Boolean> {
        return try {
            val result = authProvider.signOut()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
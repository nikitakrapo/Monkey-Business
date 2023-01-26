package com.nikitakrapo.account.firebase

import com.nikitakrapo.account.models.AccountUpdateRequest
import com.nikitakrapo.account.models.Account
import kotlinx.coroutines.flow.StateFlow

//TODO: map exceptions to [AuthException]
interface AuthProvider {

    val account: StateFlow<Account?>

    suspend fun getIdToken(forceRefresh: Boolean = false): String?

    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
    ): Account?

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Account?

    fun signOut(): Boolean

    suspend fun updateAccount(request: AccountUpdateRequest)
}
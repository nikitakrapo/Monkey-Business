package com.nikitakrapo.account.firebase

import com.nikitakrapo.account.models.Account
import com.nikitakrapo.account.exceptions.AuthException
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.cancellation.CancellationException

//TODO: map exceptions to [AuthException]
interface AuthProvider {

    val account: StateFlow<Account?>

    suspend fun getIdToken(): String?

    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
    ): Account?

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Account?

    fun signOut(): Boolean
}
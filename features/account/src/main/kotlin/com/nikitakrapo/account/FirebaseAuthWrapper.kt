package com.nikitakrapo.account

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await

class FirebaseAuthWrapper(
    private val firebaseAuth: FirebaseAuth,
) {
    private val authStateListener = AuthStateListener { onAuthStateChanged(it) }

    val currentAccountFlow: MutableStateFlow<Account?> =
        MutableStateFlow(firebaseAuth.currentUser?.toDomainModel())

    init {
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
    ): Result<Account> {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).awaitAndFetchAccount()
    }

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Result<Account> {
        return firebaseAuth.signInWithEmailAndPassword(email, password).awaitAndFetchAccount()
    }

    fun destroy() {
        firebaseAuth.removeAuthStateListener(authStateListener)
    }

    private suspend fun Task<AuthResult?>.awaitAndFetchAccount(): Result<Account> = try {
        await()
            ?.user
            ?.toDomainModel()
            ?.let(Result.Companion::success)
            ?: Result.failure(IllegalStateException("Unable to map firebase user to account"))
    } catch (e: Exception) {
        Result.failure(e)
    }

    private fun onAuthStateChanged(auth: FirebaseAuth) {
        val user = auth.currentUser?.toDomainModel()
        currentAccountFlow.value = user
    }
}
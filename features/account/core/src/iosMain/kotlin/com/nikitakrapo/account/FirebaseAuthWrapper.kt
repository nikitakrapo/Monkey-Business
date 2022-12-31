package com.nikitakrapo.account

import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseAuth.FIRUser
import com.nikitakrapo.coroutines.completionHandler
import com.nikitakrapo.foundation.getCatchingNSError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.suspendCancellableCoroutine

class FirebaseAuthWrapper(
    private val firebaseAuth: FIRAuth,
) {
    val currentAccountFlow: MutableStateFlow<Account?> =
        MutableStateFlow(firebaseAuth.currentUser?.toDomainModel())

    init {
        firebaseAuth.addAuthStateDidChangeListener { auth, user ->
            user?.let { onUserChanged(it) }
        }
    }

    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
    ): Result<Account> = suspendCancellableCoroutine { continuation ->
        firebaseAuth.createUserWithEmail(
            email = email,
            password = password,
            completion = continuation.completionHandler { result ->
                result?.user?.toDomainModel()?.let { Result.success(it) }
            }
        )
    }

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Result<Account> = suspendCancellableCoroutine { continuation ->
        firebaseAuth.signInWithEmail(
            email = email,
            password = password,
            completion = continuation.completionHandler { result ->
                result?.user?.toDomainModel()?.let { Result.success(it) }
            }
        )
    }

    fun signOut() = getCatchingNSError {
        firebaseAuth.signOut(it)
    }

    private fun onUserChanged(user: FIRUser) {
        currentAccountFlow.value = user.toDomainModel()
    }
}
package com.nikitakrapo.account.firebase

import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseAuth.FIRUser
import com.nikitakrapo.account.models.Account
import com.nikitakrapo.coroutines.completionHandler
import com.nikitakrapo.foundation.getCatchingNSError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.coroutines.suspendCoroutine

@Suppress("FunctionName")
internal actual object FirebaseAuthProvider : AuthProvider {

    private val firebaseAuth = FIRAuth.auth()

    private val accountFlow: MutableStateFlow<Account?> =
        MutableStateFlow(firebaseAuth.currentUser?.toDomainModel())
    override val account: StateFlow<Account?> get() = accountFlow.asStateFlow()

    init {
        firebaseAuth.addAuthStateDidChangeListener { auth, user ->
            user?.let { onUserChanged(it) }
        }
    }

    override suspend fun getIdToken(): String? = suspendCoroutine { continuation ->
        firebaseAuth.currentUser?.getIDTokenResultWithCompletion(
            completion = continuation.completionHandler { result ->
                result?.token
            }
        )
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
    ): Account? = suspendCoroutine { continuation ->
        firebaseAuth.createUserWithEmail(
            email = email,
            password = password,
            completion = continuation.completionHandler { result ->
                result?.user?.toDomainModel()
            }
        )
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Account? = suspendCoroutine { continuation ->
        firebaseAuth.signInWithEmail(
            email = email,
            password = password,
            completion = continuation.completionHandler { result ->
                result?.user?.toDomainModel()
            }
        )
    }

    override fun signOut() = getCatchingNSError {
        firebaseAuth.signOut(it)
    }

    private fun onUserChanged(user: FIRUser) {
        accountFlow.value = user.toDomainModel()
    }
}

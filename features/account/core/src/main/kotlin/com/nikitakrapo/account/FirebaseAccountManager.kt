package com.nikitakrapo.account

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Suppress("FunctionName")
actual fun FirebaseAccountManager(): AccountManager = object : AccountManager {

    private val firebaseWrapper = FirebaseAuthWrapper(Firebase.auth)

    override val currentAccount: StateFlow<Account?> get() = firebaseWrapper.currentAccountFlow.asStateFlow()

    override suspend fun createAccount(
        email: String,
        password: String,
        username: String
    ): Result<Account> =
        firebaseWrapper.createUserWithEmailAndPassword(
            email = email,
            password = password
        )

    override suspend fun login(
        email: String,
        password: String
    ): Result<Account> =
        firebaseWrapper.signInWithEmailAndPassword(
            email = email,
            password = password
        )

    override fun logout(): Result<Unit> {
        firebaseWrapper.signOut()
        return Result.success(Unit)
    }
}
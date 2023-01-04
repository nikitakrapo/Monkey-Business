package com.nikitakrapo.account

import cocoapods.FirebaseAuth.FIRAuth
import kotlinx.coroutines.flow.StateFlow

@Suppress("FunctionName")
actual fun FirebaseAccountManager(): AccountManager = object : AccountManager {

    private val firebaseAuthWrapper = FirebaseAuthWrapper(FIRAuth.auth())

    override val currentAccount: StateFlow<Account?>
        get() = firebaseAuthWrapper.currentAccountFlow

    override suspend fun createAccount(
        email: String,
        password: String,
        username: String
    ): Result<Account> {
        return firebaseAuthWrapper.createUserWithEmailAndPassword(
            email = email,
            password = password
        )
    }

    override suspend fun login(email: String, password: String): Result<Account> {
        return firebaseAuthWrapper.signInWithEmailAndPassword(email = email, password = password)
    }

    override fun logout(): Result<Unit> {
        return firebaseAuthWrapper.signOut().map {}
    }
}

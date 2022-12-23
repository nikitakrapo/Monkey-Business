package com.nikitakrapo.account

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirebaseAccountManager(
    private val firebaseWrapper: FirebaseAuthWrapper,
) : AccountManager {

    override val currentAccount: StateFlow<Account?> get() = firebaseWrapper.currentAccountFlow.asStateFlow()

    override suspend fun createAccount(email: String, password: String): Result<Account> =
        firebaseWrapper.createUserWithEmailAndPassword(
            email = email,
            password = password
        )

    override suspend fun login(email: String, password: String): Result<Account> =
        firebaseWrapper.signInWithEmailAndPassword(
            email = email,
            password = password
        )

    override fun logout() {
        firebaseWrapper.signOut()
    }
}
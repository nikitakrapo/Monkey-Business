package com.nikitakrapo.account.firebase

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nikitakrapo.account.models.Account
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

@Suppress("FunctionName")
internal actual object FirebaseAuthProvider: AuthProvider {

    private val firebaseAuth = Firebase.auth

    private val accountFlow: MutableStateFlow<Account?> =
        MutableStateFlow(firebaseAuth.currentUser?.toDomainModel())
    override val account: StateFlow<Account?> get() = accountFlow.asStateFlow()

    init {
        firebaseAuth.addAuthStateListener { auth ->
            val user = auth.currentUser?.toDomainModel()
            accountFlow.value = user
        }
    }

    override suspend fun getIdToken(): String? {
        return firebaseAuth.currentUser?.getIdToken(false)
            ?.await()
            ?.token
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
    ): Account? {
        return firebaseAuth.createUserWithEmailAndPassword(email, password)
            .await()
            .user
            ?.toDomainModel()
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Account? {
        return firebaseAuth.signInWithEmailAndPassword(email, password)
            .await()
            .user
            ?.toDomainModel()
    }

    override fun signOut(): Boolean {
        firebaseAuth.signOut()
        return true
    }
}
package com.nikitakrapo.account.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.nikitakrapo.account.models.Account
import com.nikitakrapo.account.models.AccountUpdateRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

@Suppress("FunctionName")
internal actual object FirebaseAuthProvider : AuthProvider {

    private val firebaseAuth = Firebase.auth

    private val accountFlow: MutableStateFlow<Account?> =
        MutableStateFlow(firebaseAuth.currentUser?.toDomainModel())
    override val account: StateFlow<Account?> get() = accountFlow.asStateFlow()

    init {
        firebaseAuth.addAuthStateListener(::updateUser)
    }

    override suspend fun getIdToken(forceRefresh: Boolean): String? {
        return firebaseAuth.currentUser?.getIdToken(forceRefresh)
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

    override suspend fun updateAccount(request: AccountUpdateRequest) {
        val firebaseRequest = userProfileChangeRequest {
            request.username?.let { displayName = it }
        }
        firebaseAuth.currentUser?.updateProfile(firebaseRequest)?.await()
        updateUser(firebaseAuth)
    }

    private fun updateUser(firebaseAuth: FirebaseAuth) {
        val user = firebaseAuth.currentUser?.toDomainModel()
        accountFlow.value = user
    }
}

package com.nikitakrapo.account

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirebaseAccountManager(
    private val firebaseWrapper: FirebaseAuthWrapper,
) : AccountManager {

    override val currentAccount: StateFlow<Account?> = firebaseWrapper.currentAccountFlow.asStateFlow()
}
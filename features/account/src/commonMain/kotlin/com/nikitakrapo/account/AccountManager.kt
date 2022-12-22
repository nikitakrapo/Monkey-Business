package com.nikitakrapo.account

import kotlinx.coroutines.flow.StateFlow

interface AccountManager {
    val currentAccount: StateFlow<Account?>
}
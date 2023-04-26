package com.nikitakrapo.account

import com.nikitakrapo.monkeybusiness.network.auth.BearerTokenProvider

class BearerTokenProviderImpl(
    private val accountManager: AccountManager,
) : BearerTokenProvider {
    override suspend fun getToken(): String? {
        return accountManager.getToken().getOrNull()
    }
}

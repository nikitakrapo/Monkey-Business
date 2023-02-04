package com.nikitakrapo.account

import com.nikitakrapo.monkeybusiness.network.auth.BearerTokensProvider
import io.ktor.client.plugins.auth.providers.BearerTokens

class BearerTokensProviderImpl(
    private val accountManager: AccountManager,
) : BearerTokensProvider {
    override suspend fun getTokens(): BearerTokens? {
        val token = accountManager.getToken().getOrNull() ?: return null
        return BearerTokens(accessToken = token, refreshToken = "")
    }

    // TODO: figure out if it's okay
    override suspend fun refreshToken(previousTokens: BearerTokens?): BearerTokens? {
        return null
    }
}
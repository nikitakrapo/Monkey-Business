package com.nikitakrapo.monkeybusiness.network.auth

import io.ktor.client.plugins.auth.providers.BearerTokens

interface BearerTokensProvider {
    suspend fun getTokens(): BearerTokens?
}

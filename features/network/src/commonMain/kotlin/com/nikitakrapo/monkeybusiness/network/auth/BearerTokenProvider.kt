package com.nikitakrapo.monkeybusiness.network.auth

interface BearerTokenProvider {
    suspend fun getToken(): String?
}

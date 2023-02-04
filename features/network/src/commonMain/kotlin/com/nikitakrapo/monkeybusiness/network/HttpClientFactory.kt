package com.nikitakrapo.monkeybusiness.network

import com.nikitakrapo.monkeybusiness.network.auth.BearerTokensProvider
import com.nikitakrapo.monkeybusiness.network.auth.ClientAuth
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import kotlin.time.Duration.Companion.seconds

object HttpClientFactory {
    fun createHttpClient(
        baseUrl: String,
        clientAuth: ClientAuth,
    ) = httpClient {
        install(HttpTimeout) {
            requestTimeoutMillis = 60.seconds.inWholeMilliseconds
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.DEFAULT
        }

        installAuth(clientAuth)

        defaultRequest {
            url(baseUrl)
        }
    }

    private fun HttpClientConfig<*>.installAuth(
        clientAuth: ClientAuth,
    ) = install(Auth) {
        when (clientAuth) {
            is ClientAuth.Bearer -> installBearerAuth(
                bearerTokensProvider = clientAuth.bearerTokensProvider,
            )
            ClientAuth.None -> {}
        }
    }

    private fun Auth.installBearerAuth(
        bearerTokensProvider: BearerTokensProvider,
    ) = bearer {
        loadTokens(bearerTokensProvider::getTokens)
        refreshTokens { bearerTokensProvider.refreshToken(oldTokens) }
    }
}

package com.nikitakrapo.monkeybusiness.network

import com.nikitakrapo.monkeybusiness.network.auth.ClientAuth
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
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

        install(ContentNegotiation) {
            json()
        }

        defaultRequest {
            url(baseUrl)
            if (clientAuth is ClientAuth.Bearer) {
                val token = runBlocking { clientAuth.bearerTokensProvider.getTokens()?.accessToken }
                token?.let { header(HttpHeaders.Authorization, "Bearer $it") }
            }
        }
    }
}

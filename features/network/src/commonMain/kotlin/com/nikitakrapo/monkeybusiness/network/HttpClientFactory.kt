package com.nikitakrapo.monkeybusiness.network

import com.nikitakrapo.monkeybusiness.network.auth.BearerTokenProvider
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlin.time.Duration.Companion.seconds

class HttpClientFactory(
    private val tokenProvider: BearerTokenProvider
) {
    fun createHttpClient(baseUrl: String) = httpClient {

        install(DefaultRequest) {
            url(baseUrl)
        }

        install(ContentNegotiation) {
            json()
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 60.seconds.inWholeMilliseconds
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.DEFAULT
        }
    }.apply {
        plugin(HttpSend).intercept { request ->
            tokenProvider.getToken()?.let { token ->
                request.header(HttpHeaders.Authorization, "Bearer $token")
            }
            execute(request)
        }
    }
}

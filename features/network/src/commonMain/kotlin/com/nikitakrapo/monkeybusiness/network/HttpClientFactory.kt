package com.nikitakrapo.monkeybusiness.network

import com.nikitakrapo.monkeybusiness.network.auth.BearerTokenProvider
import io.ktor.client.call.HttpClientCall
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
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlin.time.Duration.Companion.seconds

/**
 * @param onHttpCall May be used for additional logging purposes
 */
class HttpClientFactory(
    private val tokenProvider: BearerTokenProvider,
    private val onHttpCall: (HttpClientCall) -> Unit = {},
) {
    val mainClient by lazy {
        createHttpClient(ApiUrlProvider.Main)
    }

    private fun createHttpClient(
        baseUrl: String,
    ) = httpClient {
        install(DefaultRequest) {
            url(baseUrl)
            contentType(ContentType.Application.Json)
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
            val httpCall = execute(request)
            onHttpCall(httpCall)
            httpCall
        }
    }
}

package com.nikitakrapo.monkeybusiness.network

import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

object HttpClientFactory {
    fun createHttpClient(
        logger: Logger,
    ) = httpClient {
        install(HttpTimeout) {
            requestTimeoutMillis = 60_000
        }

        install(Logging) {
            level = LogLevel.INFO
            this.logger = logger
        }
    }
}
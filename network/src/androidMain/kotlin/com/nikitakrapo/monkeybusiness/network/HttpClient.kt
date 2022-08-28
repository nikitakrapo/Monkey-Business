package com.nikitakrapo.monkeybusiness.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient = HttpClient() {

}
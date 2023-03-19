package com.nikitakrapo.monkeybusiness.finance.transactions.network

import com.nikitakrapo.monkeybusiness.finance.transactions.network.dto.TransactionRequest
import com.nikitakrapo.monkeybusiness.finance.transactions.network.dto.TransactionsResponse
import com.nikitakrapo.monkeybusiness.network.HttpClientFactory.createHttpClient
import com.nikitakrapo.monkeybusiness.network.auth.BearerTokensProvider
import com.nikitakrapo.monkeybusiness.network.auth.ClientAuth
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class TransactionsApi(
    bearerTokensProvider: BearerTokensProvider,
) {
    private val client = createHttpClient(
        baseUrl = TRANSACTIONS_HOST,
        clientAuth = ClientAuth.Bearer(bearerTokensProvider = bearerTokensProvider),
    )

    suspend fun postTransaction(request: TransactionRequest) {
        client.post("transaction") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    suspend fun getAllTransactions(): TransactionsResponse {
        val response = client.get("transactions")
        return response.body()
    }

    companion object {
        // TODO: Make changeable hosts logic
        const val TRANSACTIONS_HOST = "http://91.235.136.40"
    }
}

package com.nikitakrapo.monkeybusiness.finance.network

import com.nikitakrapo.monkeybusiness.finance.network.dto.TransactionRequest
import com.nikitakrapo.monkeybusiness.finance.network.dto.TransactionsResponse
import com.nikitakrapo.monkeybusiness.network.HttpClientFactory.createHttpClient
import com.nikitakrapo.monkeybusiness.network.auth.BearerTokensProvider
import com.nikitakrapo.monkeybusiness.network.auth.ClientAuth
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class TransactionsApi(
    firebaseTokensProvider: BearerTokensProvider,
) {
    private val client = createHttpClient(
        baseUrl = TRANSACTIONS_HOST,
        clientAuth = ClientAuth.Bearer(bearerTokensProvider = firebaseTokensProvider)
    )

    suspend fun postTransaction(request: TransactionRequest) {
        client.post("transaction") {
            setBody(request)
        }
    }

    suspend fun getAllTransactions(): TransactionsResponse {
        return client.get("transactions").body()
    }

    companion object {
        // TODO: Make changeable hosts logic
        const val TRANSACTIONS_HOST = "http://91.235.136.40"
    }
}
package com.nikitakrapo.monkeybusiness.finance.network

import com.nikitakrapo.monkeybusiness.finance.models.Transaction
import com.nikitakrapo.monkeybusiness.network.HttpClientFactory.createHttpClient
import com.nikitakrapo.monkeybusiness.network.auth.BearerTokensProvider
import com.nikitakrapo.monkeybusiness.network.auth.ClientAuth
import io.ktor.client.call.body
import io.ktor.client.request.get

class TransactionsApi(
    firebaseTokensProvider: BearerTokensProvider,
) {
    private val client = createHttpClient(
        baseUrl = TRANSACTIONS_HOST,
        clientAuth = ClientAuth.Bearer(bearerTokensProvider = firebaseTokensProvider)
    )

    suspend fun getAllTransactions() {
        val transaction: Transaction = client.get("transactions").body()
    }

    companion object {
        // TODO: Make changeable hosts logic
        const val TRANSACTIONS_HOST = "http://91.235.136.40"
    }
}
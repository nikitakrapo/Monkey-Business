package com.nikitakrapo.monkeybusiness.finance.account.remote

import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.BankAccountOpeningRequest
import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.BankAccountsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal class KtorBankAccountsApi(
    private val httpClient: HttpClient,
) : BankAccountsApi {
    override suspend fun createAccount(request: BankAccountOpeningRequest): Result<Unit> {
        return runCatching {
            httpClient.post("/bank-account") {
                setBody(request)
            }
        }
    }

    override suspend fun getAccountList(): Result<BankAccountsResponse> {
        return runCatching {
            val response = httpClient.get("/bank-accounts")
            response.body()
        }
    }
}
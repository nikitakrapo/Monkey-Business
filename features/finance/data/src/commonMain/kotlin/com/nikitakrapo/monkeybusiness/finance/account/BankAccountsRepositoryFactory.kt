package com.nikitakrapo.monkeybusiness.finance.account

import com.nikitakrapo.monkeybusiness.finance.account.remote.KtorBankAccountsApi
import io.ktor.client.HttpClient

object BankAccountsRepositoryFactory {
    fun create(httpClient: HttpClient): BankAccountsRepository {
        val api = KtorBankAccountsApi(httpClient)
        return BankAccountsRepositoryImpl(api)
    }
}
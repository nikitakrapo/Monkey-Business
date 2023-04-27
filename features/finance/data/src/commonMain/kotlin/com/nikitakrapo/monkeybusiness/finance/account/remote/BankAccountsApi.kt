package com.nikitakrapo.monkeybusiness.finance.account.remote

import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.BankAccountOpeningRequest
import com.nikitakrapo.monkeybusiness.finance.account.remote.dto.BankAccountsResponse

internal interface BankAccountsApi {
    suspend fun createAccount(request: BankAccountOpeningRequest)
    suspend fun getAccountList(): BankAccountsResponse
}